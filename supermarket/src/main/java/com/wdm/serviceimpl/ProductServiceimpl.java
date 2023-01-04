package com.wdm.serviceimpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdm.dto.ResquestProduct;
import com.wdm.entity.ProductEntity;
import com.wdm.repository.ProductRepository;
import com.wdm.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ObjectMapper objectmaper;
	
	//To get all records 
	
	public List<ResquestProduct> getAllProduct() throws IOException, SQLException {
		
		return productRepo.findAll().stream().map(t -> {
			return mapToProduct(t);
			
		}).collect(Collectors.toList());
		
		
	}
	 
	//To get the single record by given id
	
	public ProductEntity getbyId(long id) {
		
		return productRepo.findById(id).get();
	}
		
	//To post or save given records

	public ResquestProduct saveProduct(ResquestProduct Product1) {
		
		ProductEntity product = new ProductEntity(Product1.getProductName(), Product1.getManufecturingdate(),
				Product1.getProductType(), Product1.getDescription(), Product1.getSuperModelEnitity());
		
		
		productRepo.save(product);
		
		return mapToProduct(product);
	}
	
	
	

	//update the data
	public ProductEntity updateProduct(ProductEntity productmodel, long id) {

		return productRepo.save(productmodel);
	}
	
	//delete records based on given id
	public void deleteProduct(long id) {

		productRepo.deleteById(id);

	}
	
	

	@Override
	public List<Date> getunique() {
		
		return productRepo.finduniqueValue();
	}

	@Override
	public List<ProductEntity> getNuts() {
		
		return productRepo.getNutsType();
	}
	
	
	 
	
public ResquestProduct mapToProduct(ProductEntity product) {
		
		return new ResquestProduct(product.getProductName(), product.getManufecturingdate(), product.getProductType(),
				product.getDescription(), product.getSuperModelEnitity());
	}



	
}
