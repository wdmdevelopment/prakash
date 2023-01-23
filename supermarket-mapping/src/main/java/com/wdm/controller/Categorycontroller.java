package com.wdm.controller;

 
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Category;

import com.wdm.model.RequestCategory;
import com.wdm.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class Categorycontroller {

	@Autowired
	CategoryService categoryservice;

	private static final Logger logger = LogManager.getLogger(Categorycontroller.class);

	@PostMapping("/{userId}")

	public ResponseEntity<Category> saveCategory(@Valid @RequestBody RequestCategory resquestProduct,
			@PathVariable("userId") long userId) {

		logger.info("add to category");

		return new ResponseEntity<>(categoryservice.saveCategory(resquestProduct, userId), HttpStatus.CREATED);
	}

	 
	@GetMapping("/ordercategory")
	public ResponseEntity<List<Category>> getbyfiltercategory() {

		return new ResponseEntity<List<Category>>(categoryservice.findbyOrder(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id) {

		logger.info("get  category" + categoryservice.getCategory(id));

		return new ResponseEntity<Object>(categoryservice.getCategory(id), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) {

		categoryservice.deleteById(id);

		logger.info("delete the category" + id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCategory(@RequestBody RequestCategory Category, @PathVariable("id") long id) {

		categoryservice.updatecategory(Category, id);
		logger.info("update the category" + id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
