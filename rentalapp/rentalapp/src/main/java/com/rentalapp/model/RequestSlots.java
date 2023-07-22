package com.rentalapp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestSlots {
	
	@NotNull(message = "Property id is required")
	private Long propertyReservationId;
	
	@NotNull(message = "user tenant id is required")
	private Long tenantId;
	
	@NotBlank(message = "Status can't be null")
	private String Status;
	
}
