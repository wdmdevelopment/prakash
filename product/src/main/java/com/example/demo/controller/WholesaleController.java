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

import com.example.demo.model.WholesaleEntity;
import com.example.demo.service.WholesaleService;

@RestController
@RequestMapping("/wholsale")
public class WholesaleController {

	@Autowired
	WholesaleService wholesaleserivice;
	
	@PostMapping
	public WholesaleEntity saveproductWholesale(@RequestBody WholesaleEntity wholesaleentity) {
		return wholesaleserivice.saveWholesale(wholesaleentity);
		
	}
	
	
	@GetMapping("/get")
		public List<WholesaleEntity> getwholelsaleDetails(){
			
		return wholesaleserivice.getAll();
	}
	
	
	@PutMapping("/update")
		public WholesaleEntity update(@RequestBody WholesaleEntity wholesale) {
		
		return wholesaleserivice.update(wholesale);
	}
	
	@DeleteMapping("/delete/id")
		public void deletebyId(@PathVariable int id) {
		
		wholesaleserivice.delete(id);
	}
	
	
	
	
}
