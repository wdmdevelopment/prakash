package com.wdm.serviceimpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wdm.entity.Orders;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.OrderCustomException;
import com.wdm.model.RequestOrder;
import com.wdm.repository.OrderRepository;
import com.wdm.response.OrderResponse;
import com.wdm.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService {

	@Autowired
	OrderRepository OrderRepo;

	public Orders placeOrder(RequestOrder requestOrder) {
		Orders orders = new Orders();

		orders.setTotalPrice(requestOrder.getTotalPrice());
		orders.setOrdertime(requestOrder.getOrdertime());

		return OrderRepo.save(orders);
	}

	public void cancelOrder(long id) throws Exception {
		try {
			OrderRepo.deleteById(id);
		}
		catch (IdNotFoundException idNotFoundException) {
			throw new IdNotFoundException("Id not found"+ idNotFoundException);
		}
		catch (Exception exception) {
			throw new Exception(exception);
		}
	}

	public Orders updateOrder(Orders order, long id) {

		return OrderRepo.save(order);
	}

	public List<Orders> getAllOrders() {

		return OrderRepo.findAll();
	}

	public OrderResponse getOrderDetails(long orderId) throws Exception {

		Orders order;
		try {
			order = OrderRepo.findById(orderId).get();
		}

		catch (OrderCustomException e) {

			throw new OrderCustomException("Order not found for the order Id:" + orderId,
					HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.value());
		}

		catch (Exception e) {
			throw new Exception("Invaild Request"+e);
		}

		return mapToOrder(order);
	}

	private OrderResponse mapToOrder(Orders order) {

		OrderResponse orederRes = new OrderResponse();
		orederRes.setOrderStatus(order.getOrderStatus());
		orederRes.setTotalamount(order.getTotalPrice());

		return orederRes;
	}

}
