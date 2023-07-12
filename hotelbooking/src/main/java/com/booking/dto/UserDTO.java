package com.booking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

	private String userId;
	
	@NotBlank(message = "FirstName cannot be blank")
	private String firstName;

	@NotBlank(message = "LastName cannot be blank")
	private String lastName;
	 
	private String picture;
 
	@NotBlank(message = "EmailId cannot be blank")
	@Email(message = "EmailId not valid")
	private String emailId;

	//@NotBlank(message = "status cannot be blank")
	private String status;

	@NotBlank(message = "userName cannot be blank")
	private String userName;

	@NotBlank(message = "Password cannot be blank")
	@Size(max = 20, min = 8)
	private String password;

	private String role;

}
