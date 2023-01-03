package com.example.demo.controller;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

import com.example.demo.model.ProductModel;
import com.example.demo.serviceimpl.ProductServiceimpl;



@RestController
@RequestMapping("/product")

public class ProductController {

	@Autowired

	ProductServiceimpl productservice;

	@GetMapping("/statusOk")
	
	public ResponseEntity<Object> ok() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/bad")
	
	public ResponseEntity bad() {
		return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("internal")
	public ResponseEntity<Object> internal(){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@GetMapping("/get")
	//@ResponseStatus(value = HttpStatus.FOUND, reason = "files found")
	public List<ProductModel> getAllProducts() {
		return productservice.getAllProduct();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED, reason = "status accepted")
	public ProductModel saveProduct(@RequestBody ProductModel productmodel) {

		productservice.saveProduct(productmodel);

		return productmodel;

	}
	
	@GetMapping("/unique")
	
	public List<Date> getValue() {
		return productservice.getunique();
	}
	
	
	@GetMapping("/nuts")
	
	public List<ProductModel> getNutList(){
		return productservice.getNuts();
	}
	
	

	@GetMapping("/{ProductId}")
	public ProductModel getByid(@PathVariable("ProductId") int ProductId) {
		return productservice.getbyId(ProductId);
	}

	@PutMapping("/update")
	public ProductModel update(@RequestBody ProductModel productmodel) {

		productservice.saveProduct(productmodel);
		return productmodel;
	}

	@DeleteMapping("/delete/{productId}")
	public void deleteByid(@PathVariable("productId") int ProductId) {

		productservice.deleteProduct(ProductId);
	}

	
	@GetMapping("product/name")
	public ProductModel getproduct(@RequestParam("product_Id") int product_Id, @RequestParam("product_Name") String product_Name) {
		return productservice.getbyId(product_Id);
	}
	
	
	
	

}
