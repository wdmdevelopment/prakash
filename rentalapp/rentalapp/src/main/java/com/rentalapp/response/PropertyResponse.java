package com.rentalapp.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.rentalapp.entity.PropertyImage;
import com.rentalapp.entity.PropertyQuestions;
import com.rentalapp.entity.PropertySpec;
import com.rentalapp.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse extends RepresentationModel<PropertyResponse> {
	
	
	
	private Long id;
	private String type; //	private room, shared room, whole house
	private double costPerDay;
	private String roomType;
	private double space;
	private String floorAreaDesc;
	private String RentalRules;
	private String location;
	private String street;
	private String city;
	private String state;
	private String country;
	private String name;
	private String status;
 	private PropertySpec propertySpec;
	private LocalDateTime createdAt;
	private List<PropertyImage> imagePath;
	private List<PropertyQuestions> propertyQuestions;
 	private User user;
 	private double overAllRating;
 	private int ratingSize;
 	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public double getCostPerDay() {
		return costPerDay;
	}
	public String getRoomType() {
		return roomType;
	}
	public double getSpace() {
		return space;
	}
	public String getFloorAreaDesc() {
		return floorAreaDesc;
	}
	public String getRentalRules() {
		return RentalRules;
	}
	public String getLocation() {
		return location;
	}
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}
	public PropertySpec getPropertySpec() {
		return propertySpec;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public List<PropertyImage> getImagePath() {
		return imagePath;
	}
	public List<PropertyQuestions> getPropertyQuestions() {
		return propertyQuestions;
	}
	public User getUser() {
		return user;
	}
	public double getOverAllRating() {
		return overAllRating;
	}
	public int getRatingSize() {
		return ratingSize;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public void setSpace(double space) {
		this.space = space;
	}
	public void setFloorAreaDesc(String floorAreaDesc) {
		this.floorAreaDesc = floorAreaDesc;
	}
	public void setRentalRules(String rentalRules) {
		RentalRules = rentalRules;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setPropertySpec(PropertySpec propertySpec) {
		this.propertySpec = propertySpec;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setImagePath(List<PropertyImage> imagePath) {
		this.imagePath = imagePath;
	}
	public void setPropertyQuestions(List<PropertyQuestions> propertyQuestions) {
		this.propertyQuestions = propertyQuestions;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setOverAllRating(double overAllRating) {
		this.overAllRating = overAllRating;
	}
	public void setRatingSize(int ratingSize) {
		this.ratingSize = ratingSize;
	}
	
}
