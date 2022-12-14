package com.wdm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Category;
import com.wdm.exception.ProductNotFoundException;
import com.wdm.model.RequestCategory;
import com.wdm.service.CategoryService;

@RestController
@RequestMapping("/category")
public class Categorycontroller {

	@Autowired
	CategoryService categoryservice;
	
	private static final Logger logger = LogManager.getLogger(Categorycontroller.class);

	@PostMapping("/{userId}")

	public ResponseEntity<Category> saveCategory(@Valid @RequestBody RequestCategory resquestProduct, 
				@PathVariable("userId")long userId) {
		
		logger.info("add to category");
		
		return new ResponseEntity<>(categoryservice.saveCategory(resquestProduct, userId), HttpStatus.CREATED);
	}
	
	
	
	
	
	@GetMapping
	public ResponseEntity<List<RequestCategory>> getbycategory() {
		
		logger.info("get the list of category"+categoryservice.getAllcategory());
		
		List<Category> category1 = categoryservice.getAllcategory();
		
		List<RequestCategory> req = new ArrayList<>();
		for(Category category:category1) {
			
			RequestCategory reqc = new RequestCategory();
			reqc.setCategoryName(category.getCategoryName());
			
			req.add(reqc);
		}
		
		return new ResponseEntity<List<RequestCategory>>(req, HttpStatus.OK);
	}
	
	
	
	
	

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id) {
		
		
		
		 
				logger.info("get  category"+categoryservice.getCategory(id));
			
			return new ResponseEntity<Object>(categoryservice.getCategory(id), HttpStatus.OK);
		 

		

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteproduct(@PathVariable("id") long id) {
					
		long del =	categoryservice.deleteById(id);
		
		logger.info("delete the category"+id);
		
		return new ResponseEntity<Long>(del, HttpStatus.NO_CONTENT);

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCategory(@RequestBody Category Category, @PathVariable("id") long id) {

		categoryservice.updatecategory(Category, id);
		logger.info("Categorycontroller | update the category"+id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
