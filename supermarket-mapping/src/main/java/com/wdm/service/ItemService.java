package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.entity.Items;
 
import com.wdm.model.RequestItems;

@Service
public interface ItemService {
	
	
	public Cart saveItems (RequestItems requestitem);
	
	public void deleteByid(long itemId, long cartId, long userId);
	
	public Items getItemsByid(long id) throws Exception;
	
	public List<Items> getItems();
	
	 
	
	 

	
	public Items updatecategory(RequestItems items, long id);
	
}
