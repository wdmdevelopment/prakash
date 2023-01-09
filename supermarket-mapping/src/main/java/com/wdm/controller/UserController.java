package com.wdm.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
 
import com.wdm.model.RequestUserAccount;
import com.wdm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@PostMapping

	public ResponseEntity<UserAccount> saveuser(@Valid @RequestBody RequestUserAccount resquestProduct) {
		
		logger.info("Info level logger");
		
		return new ResponseEntity<>(userService.saveuser(resquestProduct), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserAccount>> getAll() {

		return new ResponseEntity<List<UserAccount>>(userService.getAlluser(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserAccount> getByid(@PathVariable("id") long id) {
		UserAccount user = userService.getuserId(id);
			if(user==null){
				throw new IdNotFoundException("Id not found"+user);
		}
		 
		return new ResponseEntity<UserAccount>(userService.getuserId(id), HttpStatus.OK);
		
 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) {
			
			userService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
