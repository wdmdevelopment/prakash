package com.wdm.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.wdm.model.RequestItems;
import com.wdm.model.ResponseCart;
import com.wdm.service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

	@Autowired

	CartService cartservice;

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@PostMapping
	public ResponseEntity<Cart> saveCart(@Valid @RequestBody RequestItems requestitem) {

		logger.info("save new product = TotalPrice={}, productId= {} ", 
				requestitem.getProductId());

		return new ResponseEntity<>(cartservice.saveCart(requestitem), HttpStatus.CREATED);
	}

//	@GetMapping
//	public ResponseEntity<List<Cart>> getAll() {
//
//		logger.info("get All cart ");
//		
////		List<Cart> cart = ;
////		
////		List<CartResponse> res = new ArrayList<>();
////		
////		for(Cart carts: cart) {
////			CartResponse rescart = new CartResponse();
////			rescart.setCartId(carts.getCartId());
////			
////			
////			
////			res.add(rescart);
////		}
//		
//		return new ResponseEntity<List<Cart>>( cartservice.getAllCart(), HttpStatus.OK);
//	}

	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCart(@PathVariable("id") long id) {
			
		logger.info("remove from the cart deleteId={}");
			
			cartservice.deleteById(id);
			
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCart(@RequestBody RequestCart Cart, @PathVariable("id") long id) {
		
		logger.info("update the cart update data={}");
		
		cartservice.updateCart(Cart, id);

		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	

	@GetMapping
	public ResponseEntity<Cart> getAllbyuser(ResponseCart responseCart) {

		  
		return new ResponseEntity<Cart>(cartservice.getCartByUser(responseCart), HttpStatus.OK);
	}
 
	
	
	
	

}
