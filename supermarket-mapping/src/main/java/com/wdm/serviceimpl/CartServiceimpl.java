package com.wdm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.entity.Items;
import com.wdm.entity.Product;
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestCart;
import com.wdm.model.RequestItems;
import com.wdm.repository.CartRepository;
import com.wdm.repository.ItemsRepository;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.service.CartService;

@Service
public class CartServiceimpl implements CartService {

	@Autowired

	CartRepository cartRepo;
	
	 
	@Autowired
	ProductMappingRespository productRepo;
	
	@Autowired
	UserAccountRespository userRepo;

	private static final Logger logger = LoggerFactory.getLogger(CartServiceimpl.class);

	public Cart saveCart(RequestItems requestitem) {

		logger.info("CartServiceimpl | cart is called");
		try {
			 
			 UserAccount userAccount = userRepo.findById(requestitem.getUserId())
						.orElseThrow(() -> new IdNotFoundException("userId not found"));
			 
			 Product product = productRepo.findById(requestitem.getProductId())
						.orElseThrow(() -> new IdNotFoundException("product id not found"));
			 
			Cart cart = cartRepo.findByOrderStatusAndUser(requestitem.getUserId(), "ACTIVE");
			 
			if(cart == null) {
				cart = new Cart();
				cart.setOrderStatus("ACTIVE");
			} 
			 
			Items items = new Items();

			items.setQuantity(requestitem.getQuantity());
			  
			 items.setProduct(product);
			 
			 double totalPriceValue =  product.getPrice() * requestitem.getQuantity();
			 
			 
			 items.setTotalPrice(totalPriceValue);
			 
			 List<Items> item2 = cart.getItem();
			
			 item2.add(items);
			   
			 cart.setItem(item2);
			  
			
			
			 cart.setUser(userAccount);
			 
			 return cartRepo.save(cart);
			 
	}
		catch (Exception e) {
			throw new ProductCustomException(e.getMessage());
	}
		
	}

	public void deleteById(long id) {

		logger.info("Cart removing is called");

		try {
			cartRepo.deleteById(id);
		} catch (Exception e) {
			 
			throw new IdNotFoundException("id not found" + e.getMessage());
		}
	}

	public Cart updateCart(RequestCart cart, long id) {

		logger.info("update the cart like add more items");
		
		Cart findById = cartRepo.findById(id).orElseThrow(()-> new IdNotFoundException("Not Found"+id));
		
		 
				
		//Items orElseThrow = itemRepo.findById(cart.getItemId()).orElseThrow(() -> new IdNotFoundException("item id not found"));
		
	//	findById.setItem((List<Items>) orElseThrow);
		
		 	
		return cartRepo.save(findById);
	}

	public List<Cart> getAllCart() {
		
		logger.info("get the all cart details");
		
		return cartRepo.findAll();

	}

	 
}
