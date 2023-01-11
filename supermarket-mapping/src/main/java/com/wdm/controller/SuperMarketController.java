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

import com.wdm.entity.Supermarket;
import com.wdm.model.RequestSuperMarket;
import com.wdm.service.SuperMarketService;

@RestController
@RequestMapping("/supermarket")
public class SuperMarketController {

	@Autowired
	SuperMarketService superMarketService;

	private static final Logger logger = LogManager.getLogger(SuperMarketController.class);

	@PostMapping
	public ResponseEntity<Supermarket> saveSuperMarket(@Valid @RequestBody RequestSuperMarket 
			resquestsupermarket) throws Exception {

		logger.info("To add new supermarket" + resquestsupermarket.getSuperMarketName());

		return new ResponseEntity<>(superMarketService.saveSuperMarket(resquestsupermarket), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")

	public ResponseEntity<Supermarket> updatedetails(@RequestBody Supermarket Supermarket, long id) {

		logger.info("To add new supermarket", id, Supermarket.getSuperMarketName(), Supermarket.getAddress());

		return new ResponseEntity<Supermarket>(superMarketService.updatesupermarket(Supermarket, id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Supermarket>> getDetails() {

		logger.info("To add new supermarket" + superMarketService.getSupermarket());

		return new ResponseEntity<List<Supermarket>>(superMarketService.getSupermarket(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")

	public ResponseEntity<Void> delete(@PathVariable("id") long id) {

		superMarketService.delete(id);

		logger.info("To delete supermarket", id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
