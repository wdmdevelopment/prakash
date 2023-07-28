package com.rentalapp.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalapp.constant.Role;
import com.rentalapp.constant.Slot;
import com.rentalapp.constant.Status;
import com.rentalapp.entity.Property;
import com.rentalapp.entity.PropertyImage;
import com.rentalapp.entity.PropertySlots;
import com.rentalapp.entity.PropertySpec;
import com.rentalapp.entity.Rating;
import com.rentalapp.entity.SearchHistory;
import com.rentalapp.entity.User;
import com.rentalapp.exception.NotFoundException;
import com.rentalapp.exception.PropertyException;
import com.rentalapp.exception.RentalAppException;
import com.rentalapp.model.PropeertySlotDateRange;
import com.rentalapp.model.RequestProperty;
import com.rentalapp.model.RequestRating;
import com.rentalapp.model.RequestUpdateProperty;
import com.rentalapp.repository.IUserAccountRespository;
import com.rentalapp.repository.PropertyImageRepository;
import com.rentalapp.repository.PropertyRepository;
import com.rentalapp.repository.PropertySpecRepository;
import com.rentalapp.repository.RatingRepository;
import com.rentalapp.repository.SearchHistoryRepository;
import com.rentalapp.response.PropertyResponse;
import com.rentalapp.utils.DateRangeUtils;
import com.rentalapp.utils.ImageUtils;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import io.imagekit.sdk.utils.Utils;

@Service
public class PropertyService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	PropertyRepository propertyRepo;

	@Autowired
	PropertyImageRepository propertyImageRepo;

	@Autowired
	IUserAccountRespository userRepo;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	RatingRepository ratingRepo;

	@Autowired
	SearchHistoryRepository searchHistoryRepo;

	@Autowired
	PropertySpecRepository propertySpecRepo;

	@Value("${file.upload-profile-dir}")
	private String fileUploadProfileDir;

	@Value("${file.upload.public-key}")
	private String publicKey;

	@Value("${file.upload.private-key}")
	private String privateKey;

	@Value("${file.upload.url}")
	private String url;

	public Property saveProperty(String requestProperty, MultipartFile[] files) {
		try {
			RequestProperty propertyObject = objectMapper.readValue(requestProperty, RequestProperty.class);

			User user = userRepo.findById(propertyObject.getHostId())
					.orElseThrow(() -> new NotFoundException("User id not found"));
			if (!user.getStatus().equals("APPROVED")) {
				throw new NotFoundException("Your account is not yet activated");
			}
			if (!user.getRole().equals("host")) {
				throw new NotFoundException("You dont have permission to add property");
			}
			Property property = new Property();
			property.setUser(user);
			property.setName(propertyObject.getName());
			property.setType(propertyObject.getType());
			property.setCostPerDay(propertyObject.getCostPerDay());
			property.setRoomType(propertyObject.getRoomType());
			property.setSpace(propertyObject.getSpace());
			property.setFloorAreaDesc(propertyObject.getFloorAreaDesc());
			property.setRentalRules(propertyObject.getRentalRules());
			property.setStreet(propertyObject.getStreet());
			property.setCity(propertyObject.getCity());
			property.setState(propertyObject.getState());
			property.setCountry(propertyObject.getCountry());
			property.setStatus(Status.ACTIVE.name().toLowerCase());
			PropertySpec propertySpec = new PropertySpec();
			propertySpec.setAc(propertyObject.isAc());
			propertySpec.setCooling(propertyObject.isCooling());
			propertySpec.setFreeparking(propertyObject.isFreeparking());
			propertySpec.setHeating(propertyObject.isHeating());
			propertySpec.setHottub(propertyObject.isHottub());
			propertySpec.setKitchen(propertyObject.isKitchen());
			propertySpec.setMaxNoOfPersonsAllowed(propertyObject.getMaxNoOfPersonsAllowed());
			propertySpec.setNoOfBathrooms(propertyObject.getNoOfBathrooms());
			propertySpec.setNoOfBedrooms(propertyObject.getNoOfBedrooms());
			propertySpec.setNoOfBeds(propertyObject.getNoOfBeds());
			propertySpec.setTelevision(propertyObject.isTelevision());
			propertySpec.setWashingMachine(propertyObject.isWashingMachine());
			propertySpec.setWirelessInternet(propertyObject.isWirelessInternet());
			property.setPropertySpec(propertySpec);
			property.setCreatedAt(LocalDateTime.now());

 			
			List<PropertySlots> slotList = new ArrayList<>();
			List<LocalDate> dateList = collectDateRange(propertyObject.getAvailableDays());
			
			property.setAvailableDays(dateList.size());

			for (LocalDate date : dateList) {
				PropertySlots slots = new PropertySlots();
				slots.setAvailableDates(date);
				slots.setProperty(property);
				slots.setPropertyStatus(Slot.AVAILABLE.name());
				slotList.add(slots);
			}

			property.setPropertySlot(slotList);

			List<PropertyImage> imageList = new ArrayList<>();
			if (files != null) {
				Path uploadPath = Paths.get(fileUploadProfileDir);

				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				for (MultipartFile file : files) {

					String fileName = ImageUtils.uploadFile(file, fileUploadProfileDir);
					ImageKit imageKit = ImageKit.getInstance();
					Configuration config = new Configuration(publicKey, privateKey, url);
					imageKit.setConfig(config);

					String base64 = Utils.fileToBase64(new File(fileName));
					// FileCreateRequest fileCreateRequest = new FileCreateRequest(base64,
					// file.getOriginalFilename());
					// Result result = imageKit.upload(fileCreateRequest);

					FileCreateRequest fileCreateRequest = new FileCreateRequest(base64, file.getOriginalFilename());
//		            	List<String> responseFields=new ArrayList<>();
//		            	responseFields.add("thumbnail");
//		            	responseFields.add("tags");
//		            	responseFields.add("customCoordinates");
//		            	fileCreateRequest.setResponseFields(responseFields);
//		            	List<String> tags=new ArrayList<>();
//		            	tags.add("Software");
//		            	tags.add("Developer");
//		            	tags.add("Engineer");
//		            	fileCreateRequest.setTags(tags);
					Result result = ImageKit.getInstance().upload(fileCreateRequest);

					System.out.println(result.getUrl());

					PropertyImage image = new PropertyImage();
					image.setImagePath(result.getUrl());
					image.setProperty(property);
					imageList.add(image);

					// PropertyImage image = new PropertyImage();
//		            	Files.copy(file.getInputStream(), uploadPath.resolve(file.getOriginalFilename()));
//		            	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//								//.path(fileUploadProfileDir)
//								.path(file.getOriginalFilename().trim())
//								.toUriString();
//						if (file.getOriginalFilename() != null) {
//							image.setImagePath(fileDownloadUri);
//						}
//						image.setProperty(property);
//						imageList.add(image);
				}

//			        if (!Files.exists(uploadPath)) {
//			        	
//			            Path createDirectories = Files.createDirectories(uploadPath);
//			            System.out.println("path="+createDirectories.getFileName());
//			            System.out.println("path2="+createDirectories.getParent());
//			            System.out.println("path3="+createDirectories.getRoot());
//			        }
//			        File file2 = ResourceUtils.getFile(String.format("classpath:%s",fileUploadProfileDir));
//					 System.out.println(file2.getAbsolutePath());
//				for (MultipartFile file : files) {
//					PropertyImage image = new PropertyImage();
//					String fileName = ImageUtils.uploadFile(file, fileUploadProfileDir);
//					String fileNameF = StringUtils.cleanPath(file.getOriginalFilename());
//					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//							//.path(fileUploadProfileDir)
//							.path(fileName.trim())
//							.toUriString();
//					if (fileName != null) {
//						image.setImagePath(fileDownloadUri);
//					}
//					image.setProperty(property);
//					imageList.add(image);
//				}
			}

			property.setImagePath(imageList);
			return propertyRepo.save(property);
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new PropertyException(e.getMessage());
		}
	}

	private List<LocalDate> collectDateRange(List<PropeertySlotDateRange> dateList) {
		
		List<LocalDate> localDateList = new ArrayList<>();
		for (PropeertySlotDateRange date: dateList) {
            String startDate = date.getStartDate();
            String endDate = date.getEndDate();
            List<LocalDate> datesInRange = DateRangeUtils.getLocalDatesInRange(startDate, endDate);
            localDateList.addAll(datesInRange);
        }
		 
		return localDateList;
	}

	public List<PropertyResponse> getAllProperty() {
		List<PropertyResponse> propertyResponseList = mapToProperty(
				propertyRepo.findAllByStatus(Status.ACTIVE.name().toLowerCase()));
		return propertyResponseList;
	}

	public List<Property> getAllPropertyFilter(Long userId, String type, String startDate, String endDate,
			Double costperday, Double minCostRange, Double maxCostRange, String roomType, String city, String state,
			String country, String name, Double overAllRating, boolean wirelessInternet, boolean cooling,
			boolean heating, boolean kitchen, boolean television, boolean freeparking, boolean ac,
			boolean washingMachine, boolean hottub, Long maxNoOfPersonsAllowed, Integer noOfBeds, Integer noOfBedrooms,
			Integer noOfBathrooms, int page, int size) {

		try {
				 
			if (userId != null) {
				User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
				SearchHistory history = new SearchHistory();
				history.setCity(city);
				history.setState(state);
				history.setCountry(country);
				history.setPropertyName(name);
				history.setMaxNoOfPersonsAllowed(maxNoOfPersonsAllowed);
				history.setNoOfBeds(noOfBeds);
				history.setRoomType(roomType);
				history.setSerachedAt(LocalDateTime.now());
				history.setUser(user);
				searchHistoryRepo.save(history);
			}

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Property> q = cb.createQuery(Property.class);
			Root<Property> root = q.from(Property.class);

			List<Predicate> predicates = new ArrayList<>();

			if (!startDate.equals("") && !endDate.equals("")) {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate startDatec = LocalDate.parse(startDate, formatter);
				LocalDate endDatec = LocalDate.parse(endDate, formatter);
				  
			        Subquery<PropertySlots> subquery = q.subquery(PropertySlots.class);
			        Root<PropertySlots> childRoot = subquery.from(PropertySlots.class);
			        subquery.select(childRoot);
				
			        Predicate dateRangePredicate = cb.between(childRoot.get("availableDates"), startDatec, endDatec);
			        subquery.where(dateRangePredicate);
			        
			        Join<Property, PropertySlots> parentToChildJoin = root.join("propertySlot");
			        Predicate existsInDateRange = parentToChildJoin.in(subquery);

			        q.select(root)
			            .distinct(true)
			            .where(cb.not(existsInDateRange));
			        
			        
				
			//	q.select(root).where(cb.between(root.join("propertySlot").get("availableDates"), startDatec, endDatec));
				
//				Predicate startPredicate = cb.equal(root.join("propertySlot").get("availableDates"),
//						startDatec);
//				Predicate endPredicate = cb.equal(root.join("propertySlot").get("availableDates"),
//						endDatec);
//				 
// 		Predicate available = cb.like(cb.lower(root.join("propertySlot").get("propertyStatus")),"%" + Slot.BOOKED + "%");
// 			Predicate compoundPredicate = cb.and(startPredicate, endPredicate, available);
//			 
// 				predicates.add(cb.not(compoundPredicate));
			}

			if (minCostRange != null && maxCostRange != null) {
				predicates.add(cb.or(cb.between(root.get("costPerDay"), minCostRange, maxCostRange)));
			}

			if (minCostRange != null && maxCostRange == null) {
				predicates.add(cb.or(cb.lessThanOrEqualTo(root.get("costPerDay"), minCostRange)));
			}
			if (maxCostRange != null && minCostRange == null) {
				predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("costPerDay"), maxCostRange)));
			}

			if (!type.equals("")) {

				predicates.add(cb.or(cb.like(cb.lower(root.get("type")), "%" + type.toLowerCase() + "%")));
			}
			if (costperday != null) {

				predicates.add(cb.or(cb.lessThanOrEqualTo(root.get("costPerDay"), +costperday)));
			}
			if (!roomType.equals("")) {

				predicates.add(cb.or(cb.like(cb.lower(root.get("roomType")), "%" + roomType.toLowerCase() + "%")));
			}
			if (!city.equals("")) {
				predicates.add(cb.or(cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%")));
			}
			if (!state.equals("")) {
				predicates.add(cb.or(cb.like(cb.lower(root.get("state")), "%" + state.toLowerCase() + "%")));
			}
			if (!country.equals("")) {
				predicates.add(cb.or(cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%")));
			}
			if (!name.equals("")) {

				predicates.add(cb.or(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")));
			}
			if (overAllRating != null) {

				predicates.add(cb.or(cb.lessThanOrEqualTo(root.get("overAllRating"), +overAllRating)));
			}
			if (wirelessInternet) {

				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("wirelessInternet"))));
			}
			if (cooling) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("cooling"))));
			}
			if (heating) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("heating"))));
			}

			if (kitchen) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("kitchen"))));
			}
			if (television) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("television"))));
			}
			if (freeparking) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("freeparking"))));

			}
			if (ac) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("ac"))));
			}
			if (washingMachine) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("washingMachine"))));
			}
			if (hottub) {
				predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("hottub"))));
			}

			if (maxNoOfPersonsAllowed != null) {
				predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("maxNoOfPersonsAllowed"),
						+maxNoOfPersonsAllowed)));
			}

			if (noOfBeds != null) {
				predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("noOfBeds"), +noOfBeds)));
			}
			if (noOfBedrooms != null) {
				predicates.add(
						cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("noOfBedrooms"), +noOfBedrooms)));
			}
			if (noOfBathrooms != null) {
				predicates.add(
						cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("noOfBathrooms"), +noOfBathrooms)));
			}

			q.where(predicates.toArray(new Predicate[0]));

			TypedQuery<Property> typedQuery = entityManager.createQuery(q);
			List<Property> resultList = typedQuery.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalAppException(e.getMessage());
		}
	}

	public Property editProperty(String requestProperty, MultipartFile[] files, long propertyId, long userId) {
		try {
			User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User id not found"));
			RequestUpdateProperty propertyObject = objectMapper.readValue(requestProperty, RequestUpdateProperty.class);
			Property updateProperty = propertyRepo.findById(propertyId)
					.orElseThrow(() -> new NotFoundException("Property id not found"));

			updateProperty.setUser(user);
			updateProperty.setName(propertyObject.getName());
			updateProperty.setType(propertyObject.getType());
			updateProperty.setCostPerDay(propertyObject.getCostPerDay());
			updateProperty.setRoomType(propertyObject.getRoomType());
			updateProperty.setSpace(propertyObject.getSpace());
			updateProperty.setFloorAreaDesc(propertyObject.getFloorAreaDesc());
			updateProperty.setRentalRules(propertyObject.getRentalRules());
			updateProperty.setStreet(propertyObject.getStreet());
			updateProperty.setCity(propertyObject.getCity());
			updateProperty.setState(propertyObject.getState());
			updateProperty.setCountry(propertyObject.getCountry());

			PropertySpec propertySpec = propertySpecRepo.findById(propertyObject.getPropertySpecId())
					.orElseThrow(() -> new NotFoundException("property Spec id not found"));

			propertySpec.setAc(propertyObject.isAc());
			propertySpec.setCooling(propertyObject.isCooling());
			propertySpec.setFreeparking(propertyObject.isFreeparking());
			propertySpec.setHeating(propertyObject.isHeating());
			propertySpec.setHottub(propertyObject.isHottub());
			propertySpec.setKitchen(propertyObject.isKitchen());
			propertySpec.setMaxNoOfPersonsAllowed(propertyObject.getMaxNoOfPersonsAllowed());
			propertySpec.setNoOfBathrooms(propertyObject.getNoOfBathrooms());
			propertySpec.setNoOfBedrooms(propertyObject.getNoOfBedrooms());
			propertySpec.setNoOfBeds(propertyObject.getNoOfBeds());
			propertySpec.setTelevision(propertyObject.isTelevision());
			propertySpec.setWashingMachine(propertyObject.isWashingMachine());
			propertySpec.setWirelessInternet(propertyObject.isWirelessInternet());
			updateProperty.setPropertySpec(propertySpec);
			updateProperty.setCreatedAt(LocalDateTime.now());

			List<PropertyImage> imageList = updateProperty.getImagePath();
			if (files != null) {
				for (MultipartFile file : files) {
					PropertyImage image = new PropertyImage();
					String fileName = ImageUtils.uploadFile(file, fileUploadProfileDir);
					if (fileName != null) {
						image.setImagePath(fileName);
					}
					image.setProperty(updateProperty);
					imageList.add(image);
				}
			}

			updateProperty.setImagePath(imageList);
			return propertyRepo.save(updateProperty);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		}
	}

	public void removePropertyImage(Long imageId, Long propertyId, Long userId) {
		try {

			User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User id not found"));
			if (user != null) {
				Optional<Property> property = propertyRepo.findById(propertyId);
				if (property.isPresent()) {
					List<PropertyImage> imagePath = property.get().getImagePath();
					Optional<PropertyImage> removeToimage = imagePath.stream().filter(e -> e.getId() == imageId)
							.findFirst();
					if (removeToimage.isPresent()) {
						imagePath.remove(removeToimage.get());
						propertyRepo.save(property.get());
					} else {
						throw new NotFoundException(
								"imagePath with id " + imageId + " not found in property with id " + propertyId);
					}
				} else {
					throw new NotFoundException("property with id " + propertyId + " not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RentalAppException(e.getMessage());
		}
	}

	public Property saveRating(RequestRating rating) {
		Property property = findByProperty(rating.getPropertyId());
		Rating rate = ratingRepo.findByPropertyIdAndUserId(rating.getPropertyId(), rating.getUserId());
		if (rate == null) {
			rate = new Rating();
		}
		User user = userRepo.findByIdAndRole(rating.getUserId(), Role.TENANT.name().toLowerCase());
		if (user != null) {
			rate.setUser(user);
			rate.setRatedAt(LocalDateTime.now());
			rate.setReview(rating.getReview());
			rate.setRating(rating.getRating());
			rate.setProperty(property);
		} else {
			throw new NotFoundException("user id not found");
		}

		ratingRepo.save(rate);
		return findByProperty(rating.getPropertyId());

	}

	public Property findByProperty(Long id) {
		return propertyRepo.findById(id).get();
	}

	private double averageRatings(long propertyId) {
		List<Rating> raitings = ratingRepo.findByPropertyId(propertyId);
		List<Double> collect = raitings.stream().map(e -> e.getRating()).collect(Collectors.toList());
		return collect.stream().mapToDouble(a -> a).average().orElse(0);
	}

	public Rating getRatingByProperty(long userId, long propertyId) {
		return ratingRepo.findByPropertyIdAndUserId(propertyId, userId);
	}

	private List<PropertyResponse> mapToProperty(List<Property> property) {
		return property.stream()
				.map(e -> new PropertyResponse(e.getId(), e.getType(), e.getCostPerDay(), e.getRoomType(), e.getSpace(),
						e.getFloorAreaDesc(), e.getRentalRules(), e.getStreet(), e.getCity(), e.getState(),
						e.getCountry(), e.getName(), e.getStatus(), e.getPropertySpec(), e.getCreatedAt(),
						e.getImagePath(), e.getPropertyQuestions(), e.getUser(), averageRatings(e.getId()),
						e.getRatings().size()))
				.collect(Collectors.toList());
	}

	public Property getPropertyById(long propertyId) {
		Optional<Property> property = propertyRepo.findById(propertyId);
		return property.get();
	}

	public List<Property> getMyPropertyById(long userId) {
		return propertyRepo.findAllByUserIdOrderByCreatedAtDesc(userId);
	}

}
