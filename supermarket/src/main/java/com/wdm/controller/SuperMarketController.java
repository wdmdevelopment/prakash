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
 
import com.wdm.dto.ResquestSuperMarket; 
import com.wdm.entity.SuperMarketEntity;
import com.wdm.exception.SuperMarketIdNotFoundException;
import com.wdm.serviceimpl.SuperMarketServiceImpl;

@RestController
@RequestMapping("super")
public class SuperMarketController {

	@Autowired

	SuperMarketServiceImpl supermodelimpl;

	@PostMapping
	public ResponseEntity<ResquestSuperMarket> saveproduct(@Valid @RequestBody ResquestSuperMarket superMarket) {
		
		return supermodelimpl.save(superMarket), HttpStatus.CREATED;

	}

	
	@GetMapping("/getsupermarket")

	public List<ResquestSuperMarket> getAllSuperModel() {
		return supermodelimpl.getAll();
	}

	
	@PutMapping("update/shop/{id}")
	public SuperMarketEntity updatesupermodel(@RequestBody SuperMarketEntity superMarket,
			@PathVariable("id") long superId) {

		SuperMarketEntity supermarket = supermodelimpl.update(superMarket, superId);

		if (supermarket == null) {

			throw new SuperMarketIdNotFoundException();

		}

		return supermarket;

	}

	@DeleteMapping("/delete/id")

	public void deleteById(@PathVariable("id") long id) {
		supermodelimpl.delete(id);
	}

}
