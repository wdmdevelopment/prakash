package com.example.demo.serviceimpl;

import java.io.File;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ProductModel;
import com.example.demo.repository.Repository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	Repository productRepo;
	
	//To get all records 
	
	public List<ProductModel> getAllProduct() {

		return productRepo.findAll();
	}

	//To get the single record by given id
	
	public ProductModel getbyId(int id) {

		return productRepo.findById(id).get();
	}
		
	//To post or save given records

	public void saveProduct(ProductModel productModel) {

		productRepo.save(productModel);
	}
	
	
	//update the data
	public void updateProduct(ProductModel productmodel, int id) {

		productRepo.save(productmodel);
	}
	
	//delete records based on given id
	public void deleteProduct(int id) {

		productRepo.deleteById(id);

	}
	
	

	@Override
	public List<Date> getunique() {
		
		return productRepo.finduniqueValue();
	}

	@Override
	public List<ProductModel> getNuts() {
		
		return productRepo.getNutsType();
	}

	
	
}
