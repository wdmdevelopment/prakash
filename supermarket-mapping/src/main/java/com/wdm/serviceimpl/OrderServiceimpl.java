package com.wdm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wdm.entity.Items;
import com.wdm.entity.Orders;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.OrderCustomException;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestItems;
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
		
		Items item = new Items();
		RequestItems req = new RequestItems();
		
		if(item.getQuantity() < req.getQuantity()) {
            throw new ProductCustomException("INSUFFICIENT_QUANTITY");
        }

		item.setQuantity(item.getQuantity() - req.getQuantity());
		
		return OrderRepo.save(orders);
	}

	public void cancelOrder(long id) throws Exception {
		try {
			OrderRepo.deleteById(id);
			Items item = new Items();
			RequestItems req = new RequestItems();
			
			item.setQuantity(item.getQuantity() + req.getQuantity());
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
			throw new Exception(e);
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
