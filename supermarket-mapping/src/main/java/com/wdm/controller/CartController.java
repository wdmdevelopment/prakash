package com.wdm.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Cart;

import com.wdm.model.RequestCart;
import com.wdm.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired

	CartService cartservice;
	
	private static final Logger logger = LogManager.getLogger(CartController.class);
	
	@PostMapping
	public ResponseEntity<Cart> saveCart(@Valid @RequestBody RequestCart resquestProduct) {
		
		logger.info("Info level logger");
		
		return new ResponseEntity<>(cartservice.saveCart(resquestProduct), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/get")
	public ResponseEntity<Cart> getAll() {

		return new ResponseEntity<Cart>(cartservice.getCart(), HttpStatus.OK);
	}

	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCart(@PathVariable("id") long id) {
					
			cartservice.deleteById(id);
			
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCart(@RequestBody Cart Cart, @PathVariable("id") long id) {

		cartservice.updateCart(Cart, id);

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	

}
