package com.wdm.service;

import org.springframework.stereotype.Service;

import com.wdm.entity.Orders;
import com.wdm.model.RequestOrder;

@Service
public interface OrderService {
	
	public Orders saveOrder(RequestOrder requestOrder);
	
	public void deleteByid(long id);
	
	public Orders updateOrder(RequestOrder requestOrder, long id);
	
	public Orders getAllOrders();

}
