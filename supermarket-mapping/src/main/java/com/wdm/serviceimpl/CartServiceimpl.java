package com.wdm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.model.RequestCart;
import com.wdm.repository.CartRepository;
import com.wdm.service.CartService;

@Service
public class CartServiceimpl implements CartService {
	
	@Autowired
	
	CartRepository cartRepo;
	
	
	
	public Cart saveCart(RequestCart requestCart) {
		Cart cart = new Cart();
		
		cart.setTotalPrice(requestCart.getTotalPrice());
		
		return cartRepo.save(cart);
	}

	
	
	
	 
	
	public void deleteById(long id) {
		
		cartRepo.deleteById(id);
		
	}






	@Override
	public Cart updateCart(RequestCart requestCart, long id) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public Cart getCart() {
		// TODO Auto-generated method stub
		return null;
	}

}
