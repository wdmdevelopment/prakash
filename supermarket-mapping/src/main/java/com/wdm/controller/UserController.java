package com.wdm.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.model.RequestLogin;
import com.wdm.model.RequestUserAccount;
import com.wdm.response.UserResponse;
import com.wdm.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@PostMapping("/create")

	public ResponseEntity<UserAccount> saveuser(@Valid @RequestBody RequestUserAccount resquestProduct) {
		
		logger.info("To create account by user/customer");
		
		return new ResponseEntity<>(userService.saveuser(resquestProduct), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserAccount>> getAll() {
		logger.info("To get all by user/customer");
		return new ResponseEntity<List<UserAccount>>(userService.getAlluser(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getByid(@PathVariable("id") long id) {
		UserResponse user = userService.getuserId(id);
		logger.info("To get  by id user/customer"+id);
			if(user==null){
				
				logger.info("if user id is null"+id);
				
				throw new IdNotFoundException("Id not found"+user);
		}
		 
		return new ResponseEntity<UserResponse>(userService.getuserId(id), HttpStatus.OK);
		
 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) throws Exception {
		logger.info("user account will delete"+id);
			userService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	
	@PostMapping("/loggin")	
	public ResponseEntity<?> loggingValidation(RequestLogin requst) throws Exception{
		
		return new ResponseEntity<>(userService.getuserbyEmail(requst.getEmailId(), requst.getPassword()), HttpStatus.OK);
		
	}
	
	

}
