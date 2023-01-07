package com.wdm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.entity.Supermarket;
import com.wdm.model.RequestSuperMarket;
import com.wdm.service.SuperMarketService;

@RestController
@RequestMapping("/supermarket")
public class SuperMarketController {
	
	
	@Autowired
	SuperMarketService superMarketService;
	
	@PostMapping
	
	public ResponseEntity<Supermarket> saveSuperMarket(@Valid @RequestBody RequestSuperMarket resquestProduct) {

		return new ResponseEntity<> (superMarketService.saveSuperMarket(resquestProduct), HttpStatus.CREATED);
	}
	
	
	

}
