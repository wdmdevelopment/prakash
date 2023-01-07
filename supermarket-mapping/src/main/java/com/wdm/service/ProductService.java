package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.Product;
import com.wdm.model.RequestProduct;

@Service
public interface ProductService {
	
	
	public Product saveProduct (RequestProduct requestProduct);
	
	public void deletebyId(long id);
	
	public Product getAllproduct();
	
	public Product updateProduct(RequestProduct requestProduct, long id);

}
