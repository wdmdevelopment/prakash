package com.wdm.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
 
import com.wdm.dto.ResquestProduct;
import com.wdm.entity.ProductEntity;  
import com.wdm.exception.SuperMarketIdNotFoundException;
import com.wdm.serviceimpl.ProductServiceimpl;

@RestController
@RequestMapping("/product")

public class ProductController {

	@Autowired

	ProductServiceimpl productservice;

	
	@GetMapping("/get")
	public List<ResquestProduct> getAllProducts() throws IOException, SQLException {
		return productservice.getAllProduct();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED, reason = "status accepted")
	public ProductEntity saveProduct(@Valid @RequestBody ResquestProduct resquestProduct) {

		return productservice.saveProduct(resquestProduct);
	}

	@GetMapping("/unique")
	public List<Date> getValue() {
		return productservice.getunique();
	}

	
	@GetMapping("/nuts")
	public List<ProductEntity> getNutList() {
		return productservice.getNuts();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ResquestProduct>  getByid(@PathVariable("productId") long productId) {
		ResquestProduct product = productservice.getbyId(productId);
			if(product==null)
			{
				return ResponseEntity.notFound().build();
			}
		
			return ResponseEntity.ok().body(product);
		
		
		
		
	}

	
	
	
	@PutMapping("/update/{id}")
	public ProductEntity update(@RequestBody ProductEntity productmodel, @PathVariable("id") long id) {

		ProductEntity productEntity = productservice.updateProduct(productmodel, id);

		if (productEntity == null) {

			throw new SuperMarketIdNotFoundException();

		}

		return productEntity;
	}

	@DeleteMapping("/delete/{productId}")
	public void deleteByid(@PathVariable("productId") long productId) {

		productservice.deleteProduct(productId);
	}

	@GetMapping("product/name")
	public ProductEntity getproduct(@RequestParam("productId") long productId,
			@RequestParam("product_Name") String productName) {
		
		return productservice.getbyId(productId, productName);
	}

}
