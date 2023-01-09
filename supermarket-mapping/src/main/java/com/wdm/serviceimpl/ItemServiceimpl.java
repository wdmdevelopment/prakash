package com.wdm.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
 
import com.wdm.exception.IdNotFoundException;
 
import com.wdm.exception.ProductCustomException;
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

		itemRepo.deleteById(id);
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

	public Items reduceQuantity(long itemId, int quantity) {
		
		Items item  = itemRepo.findById(itemId).get();
		 
		if(item.getQuantity() < quantity) {
			
			throw new ProductCustomException("Item does not have sufficient Quantity");
		}
		item.setQuantity(item.getQuantity() - quantity);
		
		return itemRepo.save(item);
		
	}

	public Items addQuantity(long itemId, int quantity) {
		
		Items item  = itemRepo.findById(itemId).get();
		 
		
		item.setQuantity(item.getQuantity() + quantity);
		
		return itemRepo.save(item);
		
	}

	 
	
		public List<Items> getMaxPrice(double maxprice) {
	
			return itemRepo.findProductsWithMaxPrice(maxprice);
		}
	
		
	
	
	

}
