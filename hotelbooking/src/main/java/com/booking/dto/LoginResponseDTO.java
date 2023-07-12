package com.booking.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
	
	private String token;
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String picture;
	private String userName;
	private List<String> roles;
	private String status;
	
}
