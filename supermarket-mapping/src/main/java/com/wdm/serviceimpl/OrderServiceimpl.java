package com.wdm.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Orders;
import com.wdm.exception.OrderCustomException;
import com.wdm.model.RequestOrder;
import com.wdm.repository.OrderRepository;
import com.wdm.response.OrderResponse;
import com.wdm.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService{
	
	
	@Autowired
	OrderRepository OrderRepo;

	
	public Orders placeOrder(RequestOrder requestOrder) {
		Orders orders = new Orders();
		
		orders.setTotalPrice(requestOrder.getTotalPrice());
		orders.setOrdertime(requestOrder.getOrdertime());
		
		return OrderRepo.save(orders);
	}


	public void deleteByid(long id) {
		
		OrderRepo.deleteById(id);
	}


	 
	public Orders updateOrder(RequestOrder requestOrder, long id) {
		 
		return null;
	}


	 
	public Orders getAllOrders() {
		 
		return null;
	}


	 
	 


	 
	public OrderResponse getOrderDetails(long orderId) throws Exception {
		
		Optional<Orders> order;
		try {
			order = OrderRepo.findById(orderId);
		}
		 
		catch(OrderCustomException e) {
			
			throw new OrderCustomException("Order not found for the order Id:" + orderId,  "NOT_FOUND", 404);
		}
		
		catch(Exception e) {
			throw new Exception("Invaild Request");
		}
		
		
			return mapToOrder(order);
	}


	private OrderResponse mapToOrder(Optional<Orders> order) {
		 
		//new OrderResponse().setOrderStatus(order.);
		
		return null;
	}
	
	
	
	
	
}
