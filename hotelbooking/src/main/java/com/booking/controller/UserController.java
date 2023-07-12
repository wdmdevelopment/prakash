package com.booking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.booking.dto.LoginDTO;
import com.booking.dto.LoginResponseDTO;
import com.booking.entity.User;
import com.booking.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/auth/user")
@CrossOrigin
@SecurityRequirement(name = "token")
public class UserController {
	
		@Autowired
		UserService userService;

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestPart("data") String userData,
				@RequestParam(value = "profile") MultipartFile file) throws Exception {
			return new ResponseEntity<User>(userService.registerUser(userData, file), HttpStatus.CREATED);
		}

		@PostMapping("/signin")
		public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDetails) throws Exception {
			return new ResponseEntity<LoginResponseDTO>(userService.login(loginDetails), HttpStatus.OK);
		}

	}
 
