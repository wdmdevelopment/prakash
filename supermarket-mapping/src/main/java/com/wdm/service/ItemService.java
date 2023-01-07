package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
import com.wdm.model.RequestItems;

@Service
public interface ItemService {
	
	
	public Items saveItems (RequestItems requestitem);
	
	public void deleteByid(long id);
	
	public Items getItems(RequestItems requestitem, long id);
	
	public Items getItems();
	
}
