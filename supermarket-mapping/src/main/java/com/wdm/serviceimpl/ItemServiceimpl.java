package com.wdm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
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

}
