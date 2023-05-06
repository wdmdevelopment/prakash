package com.wdm.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wdm.entity.Product;
import com.wdm.model.RequestLogin;
import com.wdm.model.RequestProduct;
import com.wdm.response.ProductResponse;
import com.wdm.service.ProductService;

@RestController

@RequestMapping("/product")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService productService;
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public ResponseEntity<Product> save(@Valid @RequestPart("data") String resquestProduct,
			@RequestPart("imagefile") MultipartFile file) throws IOException, SQLIntegrityConstraintViolationException {

		logger.info("save new product - resquestProduct= {}, file={} ", resquestProduct, file.getOriginalFilename());

		Product saveProduct = productService.saveProduct(resquestProduct, file);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAll() {

		logger.info(" getAllproduct product :");

		return new ResponseEntity<List<ProductResponse>>(productService.getAllproduct(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getByid(@PathVariable("id") long id) {

		logger.info("getProductById  productId : " + id);

		return new ResponseEntity<Object>(productService.getProductById(id), HttpStatus.OK);

	}

	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Product> updateProduct(@Valid @RequestPart("data") String resquestProduct,
			@RequestPart(value = "imagefile", required = false) MultipartFile file, @PathVariable("id") long id)
			throws Exception {

		logger.info("updateProduct  product : " + resquestProduct);

		return new ResponseEntity<Product>(productService.updateProduct(resquestProduct, file, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteproduct(@PathVariable("id") long id) {

		logger.info("deleteproduct  product : " + id);

		productService.deletebyId(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/productname")
	public ResponseEntity<List<ProductResponse>> filterbyproduct(
			@RequestParam(value = "productname", required = false) String name) {

		logger.info("getProductById  productId : " + name);

		return new ResponseEntity<List<ProductResponse>>(productService.filterbyId(name), HttpStatus.OK);

	}

	@GetMapping("/categoryId")
	public ResponseEntity<?> loggingValidation(@RequestParam("id") long categoryId) {

		return new ResponseEntity<>(productService.getBycategory(categoryId), HttpStatus.OK);

	}
	
	
	
	

}
