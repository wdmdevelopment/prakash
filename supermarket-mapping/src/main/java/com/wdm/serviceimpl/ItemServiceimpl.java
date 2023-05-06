package com.wdm.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.entity.Items;
import com.wdm.entity.Product;
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

//			 UserAccount userAccount = userRepo.findById(requestitem.getUserId())
//						.orElseThrow(() -> new IdNotFoundException("userId not found"));
//			 
//			 Product product = productRepo.findById(requestitem.getProductId())
//						.orElseThrow(() -> new IdNotFoundException("product id not found"));
//			 
//			Cart cart = cartRepo.findByOrderStatusAndUser(requestitem.getUserId(), "ACTIVE");
//			 
//			if(cart == null) {
//				cart = new Cart();
//				cart.setOrderStatus("ACTIVE");
//			} 
//			 
//			Items items = new Items();
//
//			items.setQuantity(requestitem.getQuantity());
//			  
//			 items.setProduct(product);
//			 
//			 double totalPriceValue =  product.getPrice() * requestitem.getQuantity();
//			 
//			 
//			 items.setTotalPrice(totalPriceValue);
//			 
//			 List<Items> item2 = cart.getItem();
//			
//			 item2.add(items);
//			   
//			 cart.setItem(item2);
//			  
//			
//			
//			 cart.setUser(userAccount);
//			 
//			 return cartRepo.save(cart);

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductCustomException("Invalid " + e.getMessage());
		}
	}

	public void deleteByid(long itemId, long cartId, long userId) {
 
		Optional<Cart> cart = cartRepo.findById(cartId);
				 
		
	      if (cart.isPresent()) {
	            Set<Items> item2 = cart.get().getItem();
	            Optional<Items> itemToRemove = item2.stream()
	                .filter(e -> e.getItemId()==itemId)
	                .findFirst();
	            
	            if (itemToRemove.isPresent()) {
	            	
	                item2.remove(itemToRemove.get());
	              
	                cartRepo.save(cart.get());
	            } else {
	                throw new EntityNotFoundException("Item with id " + itemId + " not found in cart with id " + cartId);
	            }
	        } else {
	            throw new EntityNotFoundException("Cart with id " + cartId + " not found");
	        }
	      
	      Cart cart2 = cartRepo.findByOrderStatusAndUser(userId, "ACTIVE");
          
          List<Items> item = itemRepo.findByCart_CartId(cart2.getCartId());
          
          double totalAmount= 0;
			for(Items itemcart : item) {
				 
				totalAmount += itemcart.getTotalPrice();
				
			}
			
			cart2.setTotalAmount(totalAmount);
			 
				cartRepo.save(cart2);
	     
		
		
				

//		if (Objects.equals(item.getCart().getCartId(), cartId)) {
//
//			cart.getItem().remove(item);
//
//			cartRepo.save(cart);
//
//			itemRepo.deleteById(itemId);
//		}
//
//		else {
//
//			throw new IllegalArgumentException("Cart item does not belong to cart");
//		}

	}

//		try {
//		System.out.println("------cart---------->"+id);
//		//	Items itemId = itemRepo.findById(id).orElseThrow(() -> new IdNotFoundException("item id not found"));
//		//	System.out.println(itemId.getTotalPrice());
//			
//			
//			Cart cart = cartRepo.findById(117L).get();
//			System.out.println("------cart-----1----->"+cart.getItem().size());
//			Set<Items> itemss = cart.getItem();
//			itemss.removeIf(e -> e.getItemId() == id);
//			System.out.println("------cart-----2----->"+cart.getItem().size());
//		
//			
//			return cartRepo.save(cart);

	// itemRepo.delete(itemId);

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

		double total = product.getPrice() * items.getQuantity();

		items1.setTotalPrice(total);

		items1.setProduct(product);

		Cart cart = cartRepo.findById(items1.getCart().getCartId())
				.orElseThrow(() -> new IdNotFoundException(" cart Not found "));
		
		  
		 List<Items> itemlist = itemRepo.findByCart_CartId(cart.getCartId());
			
		 
		 
			double totalAmount= 0;
			for(Items itemcart : itemlist) {
				 
				totalAmount += itemcart.getTotalPrice();
				
			}
			
			cart.setTotalAmount(totalAmount);
			
			

		cartRepo.save(cart);

		return itemRepo.save(items1);

	}

	public List<Items> getItems() {

		return itemRepo.findAll();
	}

}
