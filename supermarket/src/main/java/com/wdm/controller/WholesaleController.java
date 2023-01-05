package com.wdm.controller;

import java.util.List;

import javax.validation.Valid;

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
 
import com.wdm.dto.RequestWholesale;

import com.wdm.entity.WholesaleEntity;
import com.wdm.service.WholesaleService;

@RestController
@RequestMapping("/wholesale")
public class WholesaleController {

	@Autowired
	WholesaleService wholesaleserivice;

	

	@PostMapping
	public ResponseEntity<RequestWholesale> saveproductWholesale(@Valid @RequestBody RequestWholesale wholesaleentity) {
		
		return wholesaleserivice.saveWholesale(wholesaleentity), HttpStatus.CREATED;

	}

	@GetMapping("/get")
	public List<WholesaleEntity> getwholelsaleDetails() {

		return wholesaleserivice.getAll();
	}

	@PutMapping("/update")
	public WholesaleEntity update(@RequestBody WholesaleEntity wholesale) {

		return wholesaleserivice.update(wholesale);
	}

	@DeleteMapping("/delete/id")
	public void deletebyId(@PathVariable long id) {

		wholesaleserivice.delete(id);
	}

}
