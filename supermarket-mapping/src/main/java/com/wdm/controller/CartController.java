package com.wdm.controller;

import java.util.ArrayList;
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
import com.wdm.entity.Product;
import com.wdm.model.RequestCart;
import com.wdm.response.CartResponse;
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
		
		logger.info("get all cart"+cartservice.getAllCart());
		
		List<Cart> cart = cartservice.getAllCart();
		
		List<CartResponse> res = new ArrayList<>();
		
		for(Cart carts: cart) {
			CartResponse rescart = new CartResponse();
			rescart.setTotalPrice(carts.getTotalPrice());
			res.add(rescart);
		}
		
		return new ResponseEntity<Object>( res, HttpStatus.OK);
	}

	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCart(@PathVariable("id") long id) {
			
		logger.info("remove from the cart"+deleteCart(id));
			cartservice.deleteById(id);
			
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCart(@RequestBody Cart Cart, @PathVariable("id") long id) {
		
		logger.info("update the cart"+updateCart(Cart, id));
		
		cartservice.updateCart(Cart, id);

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	

}
