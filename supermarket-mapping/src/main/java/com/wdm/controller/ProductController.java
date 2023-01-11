package com.wdm.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.ImageProduct;
import com.wdm.entity.Product;
import com.wdm.model.RequestProduct;
import com.wdm.service.ProductService;

@RestController

@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@PostMapping (consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	
	public ResponseEntity<ImageProduct> save(@Valid @RequestPart RequestProduct resquestProduct, @RequestPart("imagefile")
		MultipartFile file) throws IOException {
 
		logger.info("save new product - productName={},category= {}, file={} ", file.getOriginalFilename()
				, resquestProduct.getProductName(), resquestProduct.getCategory(), resquestProduct.getProductImage());
		
		ImageProduct saveProduct = productService.saveProduct(resquestProduct, file);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		
		 
		
	
		logger.info(" getAllproduct product : " + getAll());
		
		return new ResponseEntity<List<Product>>(productService.getAllproduct(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id) {
		
			
			 

			logger.info("getProductById  productId : " + id);
			
			return new ResponseEntity<Object>(productService.getProductById(id), HttpStatus.OK);
		 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(RequestProduct product, @PathVariable("id")long id){
		
		 

	 
		logger.info("updateProduct  product : " + product.getProductName(), product.getStockDetails());
		
		
		
		return new ResponseEntity<Product>(productService.updateProduct(product, id), HttpStatus.OK);
	}
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) {
		
		logger.info("deleteproduct  product : " + id);
		
			  productService.deletebyId(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
 
	@GetMapping("/name")
	public ResponseEntity<List<Product>> filterbyproduct(@RequestParam(value="name") String name) {
		
			
			 

			logger.info("getProductById  productId : " + name);
			
			return new ResponseEntity<List<Product>>(productService.filterbyId(name), HttpStatus.OK);
		 
	}
	
	
	

}
