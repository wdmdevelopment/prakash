package com.wdm.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
import com.wdm.entity.Product;
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
		try {
			Items items = new Items();

			items.setQuantity(requestitem.getQuantity());
			items.setPrice(requestitem.getPrice());
			
			Product product = new Product();
			
			product.setProductName(requestitem.getProductName());
			items.setProduct(product);

			return itemRepo.save(items);
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

		items1.setPrice(items.getPrice());
		items1.setQuantity(items.getQuantity());

		return itemRepo.save(items1);

	}

	public List<Items> getItems() {

		return itemRepo.findAll();
	}

	public List<Items> getMaxPrice(double maxprice) {

		return itemRepo.findProductsWithMaxPrice(maxprice);
	}

}
