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
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/user")
	public ResponseEntity<Orders> saveOrder(@Valid @RequestBody RequestOrder resquestOrder,
			@RequestParam("user") long userId) {

		logger.info("Place the order given item TotalPrice={}, Ordertime={}", resquestOrder.getTotalAmount(),
				resquestOrder.getOrdertime());

		return new ResponseEntity<>(orderService.placeOrder(resquestOrder, userId), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> orderCancel(@PathVariable("id") long id) throws Exception {

		orderService.cancelOrder(id);

		logger.info("if Place the order cancelled", id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order, @PathVariable("id") long id) {

		logger.info("If user add extra item ", order.getOrdertime(), order.getCart().getUser().getFirstName());

		return new ResponseEntity<>(orderService.updateOrder(order, id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Orders>> getAllorders() {

		logger.info("To get all orders");

		return new ResponseEntity<List<Orders>>(orderService.getAllOrders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Orders> getorder(long id) throws Exception {

		logger.info("To get order with given id" + id);

		return new ResponseEntity<Orders>(orderService.getOrderDetails(id), HttpStatus.OK);
	}

}
