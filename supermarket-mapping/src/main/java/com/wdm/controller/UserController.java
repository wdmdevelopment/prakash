package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.UserAccount;
import com.wdm.model.RequestUserAccount;
import com.wdm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	
	public ResponseEntity<UserAccount> saveuser(@Valid @RequestBody RequestUserAccount resquestProduct) {

		return new ResponseEntity<> (userService.saveuser(resquestProduct), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
}
