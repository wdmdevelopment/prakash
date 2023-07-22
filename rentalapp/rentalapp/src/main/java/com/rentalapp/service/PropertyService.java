package com.rentalapp.service;

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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.rentalapp.entity.User;
import com.rentalapp.exception.NotFoundException;
import com.rentalapp.exception.PropertyException;
import com.rentalapp.exception.RentalAppException;
import com.rentalapp.model.RequestProperty;
import com.rentalapp.model.RequestRating;
import com.rentalapp.model.RequestUpdateProperty;
import com.rentalapp.repository.IUserAccountRespository;
import com.rentalapp.repository.PropertyImageRepository;
import com.rentalapp.repository.PropertyRepository;
import com.rentalapp.repository.PropertySpecRepository;
import com.rentalapp.repository.RatingRepository;
import com.rentalapp.response.PropertyResponse;
import com.rentalapp.utils.DateRangeUtils;
import com.rentalapp.utils.ImageUtils;

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
	PropertySpecRepository propertySpecRepo;

	@Value("${file.upload-profile-dir}")
	private String fileUploadProfileDir;

	public Property saveProperty(String requestProperty, MultipartFile[] files) {
		try {
			RequestProperty propertyObject = objectMapper.readValue(requestProperty, RequestProperty.class);
			User user = userRepo.findById(propertyObject.getTenantId())
					.orElseThrow(() -> new NotFoundException("User id not found"));
			Property property = new Property();
			property.setUser(user);
			property.setName(propertyObject.getName());
			property.setType(propertyObject.getType());
			property.setCostPerDay(propertyObject.getCostPerDay());
			property.setRoomType(propertyObject.getRoomType());
			property.setSpace(propertyObject.getSpace());
			property.setFloorAreaDesc(propertyObject.getFloorAreaDesc());
			property.setRentalRules(propertyObject.getRentalRules());
			property.setLocation(propertyObject.getLocation());
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
			
			property.setAvailableDays(propertyObject.getAvailableDays().stream().collect(Collectors.joining(", ", "[", "]")));
			List<PropertySlots> slotList = new ArrayList<>();
			
			List<LocalDate> dateList = DateRangeUtils.getDateList(propertyObject.getAvailableDays());
			
			for(LocalDate date : dateList) {
				PropertySlots slots = new PropertySlots();
				slots.setAvailableDates(date);
				slots.setProperty(property);
				slots.setPropertyStatus(Slot.AVAILABLE.name());
				slotList.add(slots);
			}
			
			property.setPropertySlot(slotList);
			 

			List<PropertyImage> imageList = new ArrayList<>();
			if (files != null) {
				for (MultipartFile file : files) {
					PropertyImage image = new PropertyImage();
					String fileName = ImageUtils.uploadFile(file, fileUploadProfileDir);
					if (fileName != null) {
						image.setImagePath(fileName);
					}
					image.setProperty(property);
					imageList.add(image);
				}
			}

			property.setImagePath(imageList);
			return propertyRepo.save(property);
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new PropertyException(e.getMessage());
		}
	}

	public List<PropertyResponse> getAllProperty() {
		List<PropertyResponse> propertyResponseList = mapToProperty(propertyRepo.findAllByStatus(Status.ACTIVE.name().toLowerCase()));
		return propertyResponseList;
	}
	public Page<Property> getAllPropertyFilter(String type,
													  String startDate,
													  String endDate,
													  Double costperday,
													  Double minCostRange,
													  Double maxCostRange,
													  String roomType,
													  String city,
													  String state, 
													  String country,
													  String name,
													  Double overAllRating,
													  boolean wirelessInternet,
													  boolean cooling,
													  boolean heating,
													  boolean kitchen,
													  boolean television,
													  boolean freeparking,
													  boolean ac,
													  boolean washingMachine,
													  boolean hottub,
													  Long maxNoOfPersonsAllowed,
													  Integer noOfBeds,
													  Integer noOfBedrooms,
													  Integer noOfBathrooms,
													  int page,
													  int size
			) {
		
		  
		
		 try {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Property> q = cb.createQuery(Property.class);
		Root<Property> root = q.from(Property.class);
		 
		List<Predicate> predicates = new ArrayList<>();
		
		 
		if(!startDate.equals("") && !endDate.equals("")) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDatec = LocalDate.parse(startDate, formatter);
			LocalDate endDatec = LocalDate.parse(endDate, formatter);
			 
			Predicate startPredicate = cb.lessThanOrEqualTo(root.join("propertySlot").get("availableDates"), startDatec);
			Predicate endPredicate = cb.greaterThanOrEqualTo(root.join("propertySlot").get("availableDates"), endDatec);
		//	Predicate available = cb.like(cb.lower(root.join("propertySlot").get("propertyStatus")),"%" + Slot.BOOKED + "%");
		     Predicate compoundPredicate  = cb.and(startPredicate,endPredicate
//		    		 ,available
		    		 );
		     predicates.add(cb.not(compoundPredicate));
		}
		
		if(minCostRange!=null && maxCostRange!=null) {
				predicates.add(cb.or(cb.between(root.get("costPerDay"), minCostRange, maxCostRange)));
		}
		
		if(minCostRange!=null && maxCostRange==null) {
			predicates.add(cb.or(cb.lessThanOrEqualTo(root.get("costPerDay"), minCostRange)));
		}
		if(maxCostRange!=null && minCostRange==null) {
			predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("costPerDay"), maxCostRange)));
		}
		
		
		
		if(!type.equals("")) {
			 
			predicates.add(cb.or(cb.like(cb.lower(root.get("type")),"%" + type.toLowerCase() + "%")));
		}
		if(costperday!=null) {
			 
			predicates.add(cb.or(cb.lessThanOrEqualTo(root.get("costPerDay"), + costperday)));
		}
		if(!roomType.equals("")) {
						
			predicates.add(cb.or(cb.like(cb.lower(root.get("roomType")),"%" + roomType.toLowerCase() + "%")));
		}
		if(!city.equals("")) {
			predicates.add(cb.or(cb.like(cb.lower(root.get("city")),"%" + city.toLowerCase() + "%")));
		}
		if(!state.equals("")) {
			predicates.add(cb.or(cb.like(cb.lower(root.get("state")),"%" + state.toLowerCase() + "%")));
		}
		if(!country.equals("")) {
			predicates.add(cb.or(cb.like(cb.lower(root.get("country")),"%" + country.toLowerCase() + "%")));
		}
		if(!name.equals("")) {
			
			predicates.add(cb.or(cb.like(cb.lower(root.get("name")),"%" + name.toLowerCase() + "%")));
		}
		if(overAllRating!=null) {
			
			predicates.add(cb.or(cb.lessThanOrEqualTo(root.get("overAllRating"), + overAllRating)));
		}
		if(wirelessInternet) {
			 
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("wirelessInternet"))));
		}
		if(cooling) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("cooling"))));
		}
		if(heating) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("heating"))));
		}
		
		if(kitchen) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("kitchen"))));
		}
		if(television) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("television"))));
		}
		if(freeparking) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("freeparking"))));
			
		}
		if(ac) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("ac"))));
		}
		if(washingMachine) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("washingMachine"))));
		}
		if(hottub) {
			predicates.add(cb.or(cb.isTrue(root.get("propertySpec").get("hottub"))));
		}
		
		if (maxNoOfPersonsAllowed != null) {
			predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("maxNoOfPersonsAllowed"),
					 + maxNoOfPersonsAllowed )));
		}
		
		if (noOfBeds != null) {
			predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("noOfBeds"),
						+ noOfBeds 	)));
		}
		if (noOfBedrooms != null) {
			predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("noOfBedrooms"),
						+ noOfBedrooms 	)));
		}
		if (noOfBathrooms != null) {
			predicates.add(cb.or(cb.greaterThanOrEqualTo(root.get("propertySpec").get("noOfBathrooms"),
						+ noOfBathrooms	)));
		}
		
		
		
		q.where(predicates.toArray(new Predicate[0]));
		 
		TypedQuery<Property> typedQuery = entityManager.createQuery(q);
					List<Property> resultList = typedQuery.getResultList();
		return convertListToPage(resultList, page, size);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 	throw new RentalAppException(e.getMessage());
		}
	}
	
	 private Page<Property> convertListToPage(List<Property> list, int page, int size) {
	     
		 Pageable pageable = PageRequest.of(page, size);
		    
		    int startIndex = (int) pageable.getOffset();
		    int endIndex = Math.min((startIndex + pageable.getPageSize()), list.size());
		    
		    List<Property> sublist = list.subList(startIndex, endIndex);

		    return new PageImpl<>(sublist, pageable, list.size());
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
			updateProperty.setLocation(propertyObject.getLocation());
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
			User user = userRepo.findByIdAndRole(rating.getUserId(), Role.TENANT.name().toLowerCase());
		if(user!=null) {
			rate.setUser(user);
			rate.setRatedAt(LocalDateTime.now());
			rate.setReview(rating.getReview());
			rate.setRating(rating.getRating());
			rate.setProperty(property);
		}else {
			throw new NotFoundException("user id not found");
		}
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
						e.getFloorAreaDesc(), e.getRentalRules(), e.getLocation(), e.getStreet(), e.getCity(),
						e.getState(), e.getCountry(), e.getName(), e.getStatus(), e.getPropertySpec(), e.getCreatedAt(),
						e.getImagePath(), e.getPropertyQuestions(), e.getUser(), averageRatings(e.getId()),
						e.getRatings().size()))
				.collect(Collectors.toList());
	}

	public Property getPropertyById(long userId, long propertyId) {
		Property property = propertyRepo.findByIdAndUserId(propertyId, userId);
		return property;
	}

}
