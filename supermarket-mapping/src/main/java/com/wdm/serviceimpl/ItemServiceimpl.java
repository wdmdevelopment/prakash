package com.wdm.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.controller.ItemsController;
import com.wdm.entity.Cart;
import com.wdm.entity.Items;
import com.wdm.entity.Product;
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestItems;
import com.wdm.repository.CartRepository;
import com.wdm.repository.ItemsRepository;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.service.ItemService;

@Service
public class ItemServiceimpl implements ItemService {

	@Autowired
	ItemsRepository itemRepo;
	
	@Autowired
	ProductMappingRespository productRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	UserAccountRespository userRepo;
	
	
	
		 
	private static final Logger logger = LogManager.getLogger(ItemServiceimpl.class);
	
	 
	
	public Cart saveItems(RequestItems requestitem) {
		try {
		 
		 
			Cart cart = cartRepo.findByUser(requestitem.getUserId());
			if(cart == null) {
				cart = new Cart();
				cart.setOrderStatus("ACTIVE");
			} 
			
			System.out.println(cart);

			Items items = new Items();

			items.setQuantity(requestitem.getQuantity());
			 
			
			Product product = productRepo.findById(requestitem.getProductId())
			.orElseThrow(() -> new IdNotFoundException("product id not found"));
			
			items.setProduct(product);
			 items.setTotalPrice(requestitem.getTotalprice());
			 
			 
			 items.setCart(cart);
			 
			 cart.setOrderStatus(requestitem.getOrderStatus());
			 
			 UserAccount userAccount = userRepo.findById(requestitem.getUserId())
						.orElseThrow(() -> new IdNotFoundException("userId not found"));
			 
			 List<Items> item = cart.getItem();
			 item.add(items);
			 
			 cart.setUser(userAccount);
			 
			 return cartRepo.save(cart);
			 
			
			
//			if(cartRepo.existsByUser(requestitem.getUserId())) {
//				 
//				Cart cart = cartRepo.findByUser(requestitem.getUserId());
//				
//				System.out.println(cart);
//
//				Items items = new Items();
//
//				items.setQuantity(requestitem.getQuantity());
//				 
//				
//				Product product = productRepo.findById(requestitem.getProductId())
//				.orElseThrow(() -> new IdNotFoundException("product id not found"));
//				
//				items.setProduct(product);
//				 items.setTotalPrice(requestitem.getTotalprice());
//				 
//				 
//				 items.setCart(cart);
//				 
//				 cart.setOrderStatus(requestitem.getOrderStatus());
//				 
//				 UserAccount userAccount = userRepo.findById(requestitem.getUserId())
//							.orElseThrow(() -> new IdNotFoundException("userId not found"));
//				 
//				 cart.setUser(userAccount);
//				 
//				 return cartRepo.save(cart);
//				 
//				 
//				
//			}
//			else {
//				
//				Cart newCart = new Cart();
//				
//				
//				Items items = new Items();
//
//				items.setQuantity(requestitem.getQuantity());
//				 
//				
//				Product product = productRepo.findById(requestitem.getProductId())
//				.orElseThrow(() -> new IdNotFoundException("product id not found"));
//				
//				items.setProduct(product);
//				 items.setTotalPrice(requestitem.getTotalprice());
//				 
//				 items.setCart(newCart);
//				 
//				 newCart.setOrderStatus(requestitem.getOrderStatus());
//				 
//				 UserAccount userAccount = userRepo.findById(requestitem.getUserId())
//							.orElseThrow(() -> new IdNotFoundException("userId not found"));
//				 
//				 newCart.setUser(userAccount);
//				 
//				 return cartRepo.save(newCart);
//				
//			}
			
			 
		} catch (Exception e) {
			throw new ProductCustomException("Invalid " + e.getMessage());
		}
	}

	public void deleteByid(long id) {

		Optional<Items> findById = itemRepo.findById(id);
		if (findById.isPresent()) {
			itemRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Id not present");
		}
	}

	public Items getItemsByid(long id) throws Exception {
		try {
			return itemRepo.findById(id).get();

		} catch (Exception idNotFoundException) {
			throw new IdNotFoundException(idNotFoundException.getMessage());
		}

	}

	public Items updatecategory(RequestItems items, long id) {

		Items items1 = itemRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Not Found" + id));

		 
		items1.setQuantity(items.getQuantity());
		
		Product product = productRepo.findById(items.getProductId())
				.orElseThrow(() -> new IdNotFoundException("product id not found"));
				
		items1.setProduct(product);

		return itemRepo.save(items1);

	}

	public List<Items> getItems() {

		return itemRepo.findAll();
	}

	 
}
