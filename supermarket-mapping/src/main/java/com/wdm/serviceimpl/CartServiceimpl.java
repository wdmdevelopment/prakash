package com.wdm.serviceimpl;

import java.util.List;
import java.util.Set;

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
import com.wdm.model.ResponseCart;
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
	
	@Autowired
	ItemsRepository itemRepo;
	

	private static final Logger logger = LoggerFactory.getLogger(CartServiceimpl.class);

	public Cart saveCart(RequestItems requestitem) {

		logger.info("CartServiceimpl | cart is called");
		try {
			 
			 UserAccount userAccount = userRepo.findById(requestitem.getUserId())
						.orElseThrow(() -> new IdNotFoundException("userId not found"));
			 
			
			 
			 
			 
			 
			
			 
			  System.out.println("----------------");
			 
			Cart cart = cartRepo.findByOrderStatusAndUser(requestitem.getUserId(), "ACTIVE");
			
			 
			
			
			 
			if(cart == null) {
				cart = new Cart();
				cart.setOrderStatus("ACTIVE");
			} 
			  
			Product product = productRepo.findById(requestitem.getProductId())
					.orElseThrow(() -> new IdNotFoundException("product id not found"));
			
			
			Items productId = itemRepo.findByProduct_ProductIdAndCart_CartId(requestitem.getProductId(), cart.getCartId());
			
			if(productId != null) {
				
				throw new IdNotFoundException("This product already added in your cart");
			}
			 
			
			
			 
			 
			 
			
			 
			 Items item = new Items();
				item.setQuantity(requestitem.getQuantity());
				
			
			 item.setProduct(product);
				
			 double totalPriceValue =  product.getPrice() * requestitem.getQuantity();
			 
				
			 item.setTotalPrice(totalPriceValue);
			  
			 Set<Items> itemList = cart.getItem();
			 item.setCart(cart);
			 itemList.add(item);
			   
			 cart.setItem(itemList);
			  
			 List<Items> itemlist = itemRepo.findByCart_CartId(cart.getCartId());
				
			 
			 
				double totalAmount= 0;
				for(Items itemcart : itemlist) {
					 
					totalAmount += itemcart.getTotalPrice();
					
				}
				
				cart.setTotalAmount(totalAmount + totalPriceValue);
				
				
			
			 cart.setUser(userAccount);
				
			 return cartRepo.save(cart);
			 
	}
		catch (Exception e) {
			e.printStackTrace();
			throw new ProductCustomException("Invalid   " + e.getMessage());
	}
		
	}

	public void deleteById(long id) {

		logger.info("Cart removing is called");
			System.out.println("----------122------------");
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
	
	
	
	public Cart getCartByUser(ResponseCart responseCart) {
		Cart cart= new Cart();
		try {
			 
			
		cart = cartRepo.findByOrderStatusAndUser(responseCart.getUserId(), "ACTIVE");
		
		System.out.println("=========164=========");
		
		if(cart==null) {
			throw new IdNotFoundException("cart is empty");
		}
		 
		return cart;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ProductCustomException(e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	 
}
