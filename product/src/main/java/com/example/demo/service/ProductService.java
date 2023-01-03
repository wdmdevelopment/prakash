package com.example.demo.service;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ProductModel;

@Service
public interface ProductService {

	public List<ProductModel> getAllProduct();

	public void saveProduct(ProductModel productModel);

	public ProductModel getbyId(int id);

	public void updateProduct(ProductModel productmodel, int id);

	public void deleteProduct(int id);
	
	public List<Date> getunique();
	
	public List<ProductModel> getNuts();
}