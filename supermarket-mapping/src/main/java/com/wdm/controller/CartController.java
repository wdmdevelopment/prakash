package com.wdm.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@PostMapping
	public ResponseEntity<Cart> saveCart(@Valid @RequestBody RequestCart resquestProduct) {
		
		logger.info("created cart");
		
		return new ResponseEntity<>(cartservice.saveCart(resquestProduct), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<Object> getAll() {
		logger.info("get all cart");
		
		return new ResponseEntity<Object>( cartservice.getAllCart(), HttpStatus.OK);
	}

	

	@DeleteMapping("/{id}")
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
