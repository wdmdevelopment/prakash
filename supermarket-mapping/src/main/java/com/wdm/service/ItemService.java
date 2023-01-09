package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
import com.wdm.entity.Product;
import com.wdm.model.RequestItems;

@Service
public interface ItemService {
	
	
	public Items saveItems (RequestItems requestitem);
	
	public void deleteByid(long id);
	
	public Items getItemsByid(long id) throws Exception;
	
	public List<Items> getItems();
	
	
	public Items reduceQuantity(long itemId, int quantity);
	
	public Items addQuantity(long itemId, int quantity);
	
	
	public List<Items> getMaxPrice(double maxprice);

	
	
	
}
