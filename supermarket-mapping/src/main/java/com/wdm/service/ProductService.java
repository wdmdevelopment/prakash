package com.wdm.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wdm.entity.Product;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.response.ProductResponse;

@Service
public interface ProductService {
	
	
	public Product saveProduct (RequestProduct requestProduct);
	
	public void deletebyId(long id);
	
	public Product getAllproduct();
	
	public Product updateProduct(RequestProduct requestProduct, long id);
	
	public Optional<Product> getProductById(long productId);
	
	
	public Product getbyProductId(long id)throws ProductNotFoundException;

}
