package com.wdm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.entity.Orders;
import com.wdm.model.RequestOrder;
import com.wdm.model.ResponseCart;
import com.wdm.response.OrderResponse;

@Service
public interface OrderService {
	
	public Orders placeOrder(RequestOrder requestOrder);
	
	public void cancelOrder(long id) throws Exception;
	
	public Orders updateOrder(Orders order, long id);
	
	public List<Orders> getAllOrders(long userId);
	
	public Orders  getOrderDetails(long orderId) throws Exception;
	
	
	public List<Cart> getAllorderInActive(long userId);
}
