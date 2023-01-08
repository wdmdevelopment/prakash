package com.wdm.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
import com.wdm.exception.ItemNotFoundException;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestItems;
import com.wdm.repository.ItemsRepository;
import com.wdm.service.ItemService;

@Service
public class ItemServiceimpl implements ItemService  {
	
	@Autowired
	ItemsRepository itemRepo;
	
	public Items saveItems(RequestItems requestitem) {
		
		Items items = new Items();
		
		items.setQuantity(requestitem.getQuantity());
		items.setPrice(requestitem.getPrice());
		
		
		return itemRepo.save(items);
	}

	
	
	
	
	
	public void deleteByid(long id) {
		
		itemRepo.deleteById(id);
	}






	@Override
	public Items getItems(RequestItems requestitem, long id) {
		 
		return null;
	}






	 
	public Items getItems() {
		 
		return null;
	}


 
	 
	public void reduceQuantity(long itemId, int quantity) {
		
		 
			Items item  = itemRepo.findById(itemId).orElseThrow( -> new ItemNotFoundException("Item with given Id not found"));
		 
		
		if(item.getQuantity() < quantity) {
			
			throw new ProductCustomException("Item does not have sufficient Quantity");
		}
		item.setQuantity(item.getQuantity() - quantity);
		
		itemRepo.save(item);
		
	}
	
	
	public void addQuantity(long itemId, int quantity) {
		
		Items item  = itemRepo.findById(itemId).orElseThrow( -> new ItemNotFoundException("Item with given Id not found"));
		 
		
		item.setQuantity(item.getQuantity() + quantity);
		
		itemRepo.save(item);
		
	}
	
		
		
	
	
	
	

}
