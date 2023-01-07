package com.wdm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wdm.entity.Orders;
import com.wdm.model.RequestOrder;
import com.wdm.repository.OrderRepository;
import com.wdm.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService{
	
	
	@Autowired
	OrderRepository OrderRepo;

	
	public Orders saveOrder(RequestOrder requestOrder) {
		Orders orders = new Orders();
		
		orders.setTotalPrice(requestOrder.getTotalPrice());
		orders.setOrdertime(requestOrder.getOrdertime());
		
		return OrderRepo.save(orders);
	}


	public void deleteByid(long id) {
		
		OrderRepo.deleteById(id);
	}


	@Override
	public Orders updateOrder(RequestOrder requestOrder, long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Orders getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
