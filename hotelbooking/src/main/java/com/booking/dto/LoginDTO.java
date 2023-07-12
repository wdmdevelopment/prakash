package com.booking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginDTO {
	
	@NotBlank(message = "Email is required")
	@Email(message = "Inccorect email pattern")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(max = 20, min = 8, message = "Password length is minimum 8 and maximum 20")
	private String password;
}
