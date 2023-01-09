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

import com.wdm.entity.Orders;
import com.wdm.model.RequestOrder;
import com.wdm.response.OrderResponse;
import com.wdm.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	@PostMapping

	public ResponseEntity<Orders> saveOrder(@Valid @RequestBody RequestOrder resquestProduct) {
		
		logger.info("Info level logger");
		
		return new ResponseEntity<>(orderService.placeOrder(resquestProduct), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cancelOrder/{id}")
	public ResponseEntity<Void> orderCancel(@PathVariable("id") long id) throws Exception{
			orderService.cancelOrder(id);
		return new  ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PutMapping("/additem/{id}")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order, @PathVariable("id") long id)	{
		
		return new  ResponseEntity<>(orderService.updateOrder(order, id), HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/Allorder")
	public ResponseEntity<List<Orders>> getAllorders() {

		return new ResponseEntity<List<Orders>>(orderService.getAllOrders(), HttpStatus.OK);
	}
	
	
	@GetMapping("/orderdetail")
	public ResponseEntity<OrderResponse> getorder(long id) throws Exception {
		
		return new ResponseEntity<OrderResponse>(orderService.getOrderDetails(id), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
