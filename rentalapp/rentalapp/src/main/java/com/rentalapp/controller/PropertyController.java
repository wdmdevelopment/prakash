package com.rentalapp.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rentalapp.entity.Property;
import com.rentalapp.entity.Rating;
import com.rentalapp.model.RequestRating;
import com.rentalapp.response.PropertyResponse;
import com.rentalapp.service.PropertyModelAssembler;
import com.rentalapp.service.PropertyService;

@RequestMapping("/property")
@RestController
public class PropertyController {

	private static final Logger logger = LogManager.getLogger(PropertyController.class);
	
	@Autowired
	PropertyService propertyService;
	
	@Autowired
	private PropertyModelAssembler propertyModelAssembler;

	@Autowired
	private PagedResourcesAssembler<Property> pagedResourcesAssembler;
	

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveProperty(@RequestPart("propertyData") String resquestProperty,
			@RequestPart(value = "images") MultipartFile[] images) {
		Property property = propertyService.saveProperty(resquestProperty, images);
		logger.info("Property added successfully");
		return new ResponseEntity<Property>(property, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getAllProperty() {
//		logger.info("Get all Property information");
//		List<PropertyResponse> allProperty = propertyService.getAllProperty();
//		logger.info("Get all Property successfully with property size={}", allProperty.size());
//		return new ResponseEntity<List<PropertyResponse>>(allProperty, HttpStatus.OK);
		return null;
	}
	
	
	@GetMapping("/{propertyId}/{userId}")
		public ResponseEntity<Property> getPropertyById(@PathVariable("propertyId") long propertyId,
				@PathVariable("userId") long userId) {
		Property productResponse = propertyService.getPropertyById(userId, propertyId);
			return new ResponseEntity<Property>(productResponse, HttpStatus.OK);
		}
	 
	 

	@GetMapping("/search")
	public ResponseEntity<?> searchProperty(@RequestParam(defaultValue = "", value = "type") String type,
									
									@RequestParam(required = false, value = "startcostRange") Double startcostRange,
									@RequestParam(required = false, value = "endcostRange") Double endcostRange,
									@RequestParam(defaultValue = "", value = "startDate") String startDate,
									@RequestParam(defaultValue = "", value = "endDate") String endDate,
									@RequestParam(required = false, value = "costperday") Double costperday,
									@RequestParam(defaultValue = "", value = "roomType") String roomType,
									@RequestParam(defaultValue = "", value = "city") String city,
									@RequestParam(defaultValue = "", value = "state") String state,
									@RequestParam(defaultValue = "", value = "country") String country,
									@RequestParam(defaultValue = "", value = "name") String name,
									@RequestParam(required = false, value = "overAllRating") Double overAllRating,
									@RequestParam(required = false, value = "wirelessInternet") boolean wirelessInternet,
									@RequestParam(required = false, value = "cooling") boolean cooling,
									@RequestParam(required = false, value = "heating") boolean heating,
									@RequestParam(required = false, value = "kitchen") boolean kitchen,
									@RequestParam(required = false, value = "television") boolean television,
									@RequestParam(required = false, value = "freeparking") boolean freeparking,
									@RequestParam(required = false, value = "ac") boolean ac,
									@RequestParam(required = false, value = "washingMachine") boolean washingMachine,
									@RequestParam(required = false, value = "hottub") boolean hottub,
									@RequestParam(required = false, value = "maxNoOfPersonsAllowed") Long maxNoOfPersonsAllowed,
									@RequestParam(required = false, value = "noOfBeds") Integer noOfBeds,
									@RequestParam(required = false, value = "noOfBedrooms") Integer noOfBedrooms,
									@RequestParam(required = false, value = "noOfBathrooms") Integer noOfBathrooms,
									@RequestParam(defaultValue = "0") int page,
							        @RequestParam(defaultValue = "10") int size
	) {
		  
		logger.info("search by property name ={} : " + type);
		Page<Property> productResponseList = propertyService.getAllPropertyFilter(type, startDate,
				endDate, costperday, startcostRange, endcostRange, roomType, city, state,
				country, name, overAllRating, wirelessInternet, cooling, heating,
				kitchen, television, freeparking, ac, washingMachine, hottub, maxNoOfPersonsAllowed, 
				noOfBeds, noOfBedrooms, noOfBathrooms, page, size);
		logger.info("get all property ={} : " + productResponseList.getSize());
		return new ResponseEntity<PagedModel<PropertyResponse>>(pagedResourcesAssembler
					.toModel(productResponseList, propertyModelAssembler), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateUser(@Valid @RequestPart("updatePropertyData") String requestProperty,
			@RequestPart(value = "updateImages", required = false) MultipartFile[] files, @PathVariable("id") long id,
			@PathVariable("userId") long userId) throws Exception {
		logger.info("update property - resquestproperty= {}, file={} ", requestProperty);
		return new ResponseEntity<Property>(propertyService.editProperty(requestProperty, files, id, userId),
				HttpStatus.OK);
	}

	@DeleteMapping("/{imageId}/{propertyId}/{userId}")
	public ResponseEntity<?> deleteImageById(@PathVariable("imageId") long imageId,
			@PathVariable("propertyId") long propertyId, @PathVariable("userId") long userId) {
		logger.info("To get image id data={}", imageId);
		propertyService.removePropertyImage(imageId, propertyId, userId);
		logger.info("Property image remove successfully");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/ratings")
	public ResponseEntity<Property> saveRating(@Valid @RequestBody RequestRating rating) {
		Property productResponse = propertyService.saveRating(rating);
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	
	@GetMapping("/{userId}/{propertyId}")
	public ResponseEntity<Rating> getRating(@PathVariable("userId") long userId,
			@PathVariable("propertyId") long propertyId) {
		Rating productResponse = propertyService.getRatingByProperty(userId, propertyId);
		return new ResponseEntity<Rating>(productResponse, HttpStatus.OK);
	}

}
