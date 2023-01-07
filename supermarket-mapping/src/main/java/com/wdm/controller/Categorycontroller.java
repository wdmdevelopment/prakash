package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Category;
import com.wdm.model.RequestCategory;
import com.wdm.service.CategoryService;

@RestController
@RequestMapping("/category")
public class Categorycontroller {
	
	@Autowired
	CategoryService categoryservice;
	
	@PostMapping
	
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody RequestCategory resquestProduct) {

		return new ResponseEntity<> (categoryservice.saveCategory(resquestProduct), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
}
