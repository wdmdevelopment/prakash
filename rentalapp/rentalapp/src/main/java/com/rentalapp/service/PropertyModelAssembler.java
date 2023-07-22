package com.rentalapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rentalapp.controller.PropertyController;
import com.rentalapp.entity.Property;
import com.rentalapp.response.PropertyResponse;

@Component
public class PropertyModelAssembler extends RepresentationModelAssemblerSupport<Property, PropertyResponse> {

	
	
	public PropertyModelAssembler() {
		super(PropertyController.class, PropertyResponse.class);
		 
	}

	@Override
	public PropertyResponse toModel(Property entity) {
		PropertyResponse model = new PropertyResponse();
	        // Both CustomerModel and Customer have the same property names. So copy the values from the Entity to the Model
	        BeanUtils.copyProperties(entity, model);
	        return model;
		 
	}

}
