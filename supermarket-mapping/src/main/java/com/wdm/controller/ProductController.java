package com.wdm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.Product;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestProduct;
import com.wdm.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@PostMapping("/save")
	public ResponseEntity<Product> save(@Valid @RequestBody RequestProduct resquestProduct) {
 
		logger.info("save new product - productName={},category= {} " , resquestProduct.getProductName(), resquestProduct.getCategory());
		
		return new ResponseEntity<>(productService.saveProduct(resquestProduct), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		
		logger.info("ProductController | getProduct  is called");
		
	
		logger.info("ProductController | getAllproduct | product : " + getAll());
		
		return new ResponseEntity<List<Product>>(productService.getAllproduct(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id) {
		
			
			logger.info("ProductController | getProductById is called");

			logger.info("ProductController | getProductById | productId : " + id);
			
			return new ResponseEntity<Object>(productService.getProductById(id), HttpStatus.OK);
		 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(RequestProduct product, @PathVariable("id")long id){
		
		logger.info("ProductController | updateProduct is called");

		logger.info("ProductController | updateProduct | productId : " + id);
		logger.info("ProductController | updateProduct | product : " + product);
		
		
		
		return new ResponseEntity<Product>(productService.updateProduct(product, id), HttpStatus.OK);
	}
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) {
		logger.info("ProductController | deleteproduct | product : " + id);
			  productService.deletebyId(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	
	
	
	@PostMapping(consumes = MediaType.ALL_VALUE)
	
	public 	Product uploadFile(@RequestPart MultipartFile file)
			throws IOException, SerialException, SQLException, ProductNotFoundException {
		
		logger.info("ProductController |  | product image : " + file);
		
		if (file.isEmpty()) {
			
			logger.info("ProductController |  | product image is empty : " + file);
			
			throw new ProductNotFoundException("Please upload file");
		}

		else {
			
			return productService.store(file);
		}
	}
	
	
	
	
	
	

}
