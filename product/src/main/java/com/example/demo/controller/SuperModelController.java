package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SuperModelEntity;
import com.example.demo.serviceimpl.SuperModelServiceImpl;

@RestController
@RequestMapping("super")
public class SuperModelController {

	
	@Autowired 
	
	SuperModelServiceImpl supermodelimpl;
	
	@PostMapping
	public SuperModelEntity saveproduct(@RequestBody SuperModelEntity supermodel) {
		return supermodelimpl.save(supermodel);
		
	}
	
	@GetMapping("/getsupermarket")
	
	public List<SuperModelEntity> getAllSuperModel(){
		return supermodelimpl.getAll();
	}
	
	@PutMapping("update/shop")
	public SuperModelEntity updatesupermodel(@RequestBody SuperModelEntity supermodel) {
		
		supermodelimpl.update(supermodel);
		return supermodel; 
	}
	
	@DeleteMapping("/delete/id")
	
	public void  deleteById (@PathVariable("id") int id) {
		supermodelimpl.delete(id);
	}
	
	
	
	
	
	
	
	
	
	
	
}
