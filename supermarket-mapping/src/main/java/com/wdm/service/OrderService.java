package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.Orders;
import com.wdm.model.RequestOrder;
import com.wdm.response.OrderResponse;

@Service
public interface OrderService {
	
	public Orders placeOrder(RequestOrder requestOrder);
	
	public void deleteByid(long id);
	
	public Orders updateOrder(RequestOrder requestOrder, long id);
	
	public Orders getAllOrders();
	
	public OrderResponse  getOrderDetails(long orderId) throws Exception;
	
	

}
