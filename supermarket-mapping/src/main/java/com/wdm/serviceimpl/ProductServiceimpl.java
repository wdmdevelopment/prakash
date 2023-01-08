package com.wdm.serviceimpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.wdm.entity.Product;
 
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.ProductAlreadyExistsException;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.repository.ProductMappingRespository;

import com.wdm.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {
	
	@Autowired
	ProductMappingRespository productRepo;

	
	public Product saveProduct(RequestProduct requestProduct) {
			
		Product product = new Product();
		
		product.setProductName(requestProduct.getProductName());
		
		product.setstock(requestProduct.getStockDetails());
		 
		if(productRepo.existsById(product.getProductId())) {
			
			throw new ProductAlreadyExistsException("Product Already Exist");
		 } 
		
		return productRepo.save(product);
	}

	 
	public void deletebyId(long id) {
		try {
		 productRepo.deleteById(id);
		}
		catch(Exception e){
			
			throw new IdNotFoundException("Product with given with Id: " + id + " not found");
			
		}
		
	}
	
 
	public Product getAllproduct() {
		  
		return (Product) productRepo.findAll().stream().map(t -> {
			return mapToProduct(t);
		}).collect(Collectors.toList());
	}


	public Product getbyProductId(long id)throws ProductNotFoundException {
		Product product;
		if(productRepo.findById(id).isEmpty()) {
			
			throw new ProductNotFoundException();
		}
		else {
			product = productRepo.findById(id).get();
		}
		 
		return product;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Product updateProduct(RequestProduct requestProduct, long id) {
		
		return null;
	}





	 
	public Optional<Product> getProductById(long productId) {
		Optional<Product> product;
		try {
				product = productRepo.findById(productId);
		}
		
		catch(Exception e) {
			throw new ProductNotFoundException("Product Not found" +productId );
		}
		  
		return product;
	}
	
	
	public RequestProduct mapToProduct(Product product) {
		
		RequestProduct requestProduct = new RequestProduct();
		
		requestProduct.setProductName(product.getProductName());
		requestProduct.setStockDetails(product.getstock());
		
		
		
		return requestProduct;
	}
	
	

}
