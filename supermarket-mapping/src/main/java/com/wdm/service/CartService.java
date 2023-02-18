package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.model.RequestCart;
import com.wdm.model.RequestItems;

@Service
public interface CartService {

	
	public Cart saveCart(RequestItems requestitem);
	
	public void deleteById(long id);
	
	public Cart updateCart(RequestCart requestCart, long id);
	
	public List<Cart> getAllCart();
	
	
}
