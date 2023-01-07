package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@PostMapping
	
	public ResponseEntity<Cart> saveCart(@Valid @RequestBody RequestCart resquestProduct) {

		return new ResponseEntity<> (cartservice.saveCart(resquestProduct), HttpStatus.CREATED);
	}
	
	
	
	
	
}
