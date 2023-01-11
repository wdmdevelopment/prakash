package com.wdm.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Category;
import com.wdm.entity.Items;
 
import com.wdm.exception.IdNotFoundException;
import com.wdm.model.RequestCategory;
import com.wdm.model.RequestItems;
import com.wdm.repository.ItemsRepository;
import com.wdm.service.ItemService;

@Service
public class ItemServiceimpl implements ItemService {

	@Autowired
	ItemsRepository itemRepo;

	public Items saveItems(RequestItems requestitem) {

		Items items = new Items();

		items.setQuantity(requestitem.getQuantity());
		items.setPrice(requestitem.getPrice());

		return itemRepo.save(items);
	}

	public void deleteByid(long id) {
		
			Optional<Items> findById = itemRepo.findById(id);
		if(findById.isPresent()) {
			itemRepo.deleteById(id);
		}
		else {
			throw new IdNotFoundException("Id not present");
		}
	}

	public Items getItemsByid(long id) throws Exception {
		try {
			return itemRepo.findById(id).get();
			  
		}
		catch (IdNotFoundException idNotFoundException) {
			throw new IdNotFoundException("Id not found"+ idNotFoundException);
		}
		
		catch (Exception e) {
			throw new Exception(e);
		}
			 
		
	}
	
	public Items updatecategory(RequestItems items, long id) {
		
		Items items1 = itemRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Not Found"+id));
			
		items1.setPrice(items.getPrice());
		items1.setQuantity(items.getQuantity());
		 
		 	return itemRepo.save(items1);
		 
		 
	}
	
	
	
	
	

	public List<Items> getItems() {

		return itemRepo.findAll();
	}
	
	
	

//	private RequestItems mapToProduct(Items i) {
//			
//		RequestItems items = new RequestItems();
//		items.setPrice(i.getPrice());
//		items.setQuantity(i.getQuantity());
//		
//		return items;
//	}

	

	 
	
		public List<Items> getMaxPrice(double maxprice) {
	
			return itemRepo.findProductsWithMaxPrice(maxprice);
		}
	
		
	
	
	

}
