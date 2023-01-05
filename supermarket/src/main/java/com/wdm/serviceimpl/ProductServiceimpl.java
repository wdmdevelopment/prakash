package com.wdm.serviceimpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdm.dto.ResquestProduct;
import com.wdm.entity.Address;
import com.wdm.entity.ProductEntity;
import com.wdm.repository.ProductRepository;
import com.wdm.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

		

	public List<ResquestProduct> getAllProduct() throws IOException, SQLException {

		return productRepo.findAll().stream().map(t -> {
			return mapToProduct(t);

		}).collect(Collectors.toList());

	}

	
	
	

	public ResquestProduct getbyId(long id, String productName) {

		ProductEntity product = productRepo.findById(id).get();
		
			
			ResquestProduct reqProduct = new ResquestProduct();
			
			reqProduct.setProductName(product.getProductName());
			
			reqProduct.setDescription(product.getDescription());
			
			reqProduct.setManufecturingdate(product.getManufecturingdate());
			
			reqProduct.setProductType(product.getProductType());
			
			
			reqProduct.setResquestSuperMarket(product.getSuperModelEnitity());
			
		return reqProduct;
	}



	
	

	public ProductEntity saveProduct(ResquestProduct product1) {
			 
		ProductEntity product = new ProductEntity(product1.getProductName(), product1.getManufecturingdate(),
				product1.getProductType(), product1.getDescription(), product1.getResquestSuperMarket());

		productRepo.save(product);

		return mapToProduct(product);
	}

	
	
	
	// update the data
	public ProductEntity updateProduct(ProductEntity productmodel, long id) {

		return productRepo.save(productmodel);
	}

	
	
	
	// delete records based on given id
	public void deleteProduct(long id) {

		productRepo.deleteById(id);

	}

	
	
	
	public List<Date> getunique() {

		return productRepo.finduniqueValue();
	}

	
	
	
	public List<ProductEntity> getNuts() {

		return productRepo.getNutsType();
	}

	public ResquestProduct mapToProduct(ProductEntity product) {
			return null;
//		return new ResquestProduct(product.getProductName(), product.getManufecturingdate(), product.getProductType(),
//				product.getDescription(), product.getSuperModelEnitity());
	}




}
