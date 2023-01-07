package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Product;
import com.wdm.model.RequestProduct;
import com.wdm.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	
	@Autowired
	ProductService productService;
	
	@PostMapping
	
	public ResponseEntity<Product> save(@Valid @RequestBody RequestProduct resquestProduct) {

		return new ResponseEntity<> (productService.saveProduct(resquestProduct), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
