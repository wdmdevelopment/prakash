package com.wdm.controller;

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

	@PostMapping

	public ResponseEntity<Category> saveCategory(@Valid @RequestBody RequestCategory resquestProduct) {
		
		logger.info("Info level logger");
		return new ResponseEntity<>(categoryservice.saveCategory(resquestProduct), HttpStatus.CREATED);
	}
	
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Category>> getbycategory() {

		return new ResponseEntity<List<Category>>(categoryservice.getAllcategory(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Object>(categoryservice.getCategory(id), HttpStatus.OK);
		}

		catch (ProductNotFoundException productNotFoundException) {
			return new ResponseEntity<Object>(productNotFoundException.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) {
					
			categoryservice.deleteById(id);
			
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCategory(@RequestBody Category Category, @PathVariable("id") long id) {

		categoryservice.updatecategory(Category, id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
