package com.wdm.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.controller.CartController;
import com.wdm.entity.Cart;
import com.wdm.exception.IdNotFoundException;
import com.wdm.model.RequestCart;
import com.wdm.repository.CartRepository;
import com.wdm.service.CartService;

@Service
public class CartServiceimpl implements CartService {

	@Autowired

	CartRepository cartRepo;

	private static final Logger logger = LoggerFactory.getLogger(CartServiceimpl.class);

	public Cart saveCart(RequestCart requestCart) {

		logger.info("CartServiceimpl | cart is called");

		Cart cart = new Cart();

		cart.setTotalPrice(requestCart.getTotalPrice());

		return cartRepo.save(cart);
	}

	public void deleteById(long id) {

		logger.info("Cart removing is called");

		try {
			cartRepo.deleteById(id);
		} catch (Exception e) {

			logger.info("card id not found" + id);

			throw new IdNotFoundException("id not found" + e);
		}
	}

	public Cart updateCart(Cart Cart, long id) {

		logger.info("update the cart like add more items");

		return cartRepo.save(Cart);
	}

	public List<Cart> getAllCart() {
		
		logger.info("get the all cart details");
		
		return cartRepo.findAll();

	}

	private RequestCart mapToCart(Cart c) {
		RequestCart cart = new RequestCart();
		cart.setTotalPrice(c.getTotalPrice());
		return cart;
	}

}
