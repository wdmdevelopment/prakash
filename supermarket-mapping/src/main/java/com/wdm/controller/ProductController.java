package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Product;
import com.wdm.exception.ProductNotFoundException;
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
	
	
	@GetMapping("/get")
	public ResponseEntity<Product> getAll(){
		
		
		return new ResponseEntity<Product> (productService.getAllproduct(), HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id ){
		try {
			return new ResponseEntity<Object>(productService.getbyProductId(id), HttpStatus.OK);
		}
		
		catch(ProductNotFoundException productNotFoundException) {
			return new ResponseEntity<Object>(productNotFoundException.getMessage(), HttpStatus.CONFLICT);
		}
	
		
	
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id){
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
}
