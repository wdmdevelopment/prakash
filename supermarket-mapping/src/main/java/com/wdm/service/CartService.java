package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.model.RequestCart;

@Service
public interface CartService {

	
	public Cart saveCart(RequestCart requestCart);
	
	public void deleteById(long id);
	
	public Cart updateCart(Cart requestCart, long id);
	
	public Cart getCart();
	
	
}
