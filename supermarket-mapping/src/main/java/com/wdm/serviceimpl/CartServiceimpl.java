package com.wdm.serviceimpl;

import java.util.stream.Collectors;

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
	
	
	

	public Cart updateCart(Cart Cart, long id) {

		return cartRepo.save(Cart);
	}
	
	

	public Cart getCart() {

		return (Cart) cartRepo.findAll().stream().map(c -> {
			return mapToCart(c);
		}).collect(Collectors.toList());
	}



	private RequestCart mapToCart(Cart c) {
		RequestCart cart = new RequestCart();
		cart.setTotalPrice(c.getTotalPrice());
		return cart;
	}

 
}
