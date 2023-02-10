package com.wdm.serviceimpl;

import java.rmi.NotBoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.entity.Items;
import com.wdm.entity.Orders;
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.OrderCustomException;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestItems;
import com.wdm.model.RequestOrder;
import com.wdm.repository.CartRepository;
import com.wdm.repository.ItemsRepository;
import com.wdm.repository.OrderRepository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService {

	@Autowired
	OrderRepository OrderRepo;

	@Autowired
	UserAccountRespository userRepo;

	@Autowired
	CartRepository cartRepo;

	@Autowired
	ItemsRepository itemRepo;

	public Orders placeOrder(RequestOrder requestOrder, long userId) {

		Orders orders = new Orders();
		try {

			Cart findBycart = cartRepo.findById(userId)
					.orElseThrow(() -> new IdNotFoundException(userId + " Not Found "));

			orders.setCart(findBycart);

			UserAccount userAccount = userRepo.findById(requestOrder.getUserId())
					.orElseThrow(() -> new IdNotFoundException("user id not found"));

			orders.setUser(userAccount);

			orders.setOrdertime(requestOrder.getOrdertime());

			Items findByItem = itemRepo.findById(userId)
					.orElseThrow(() -> new IdNotFoundException(userId + " Not Found "));

			if (findByItem.getQuantity() < requestOrder.getQuantity()) {
				throw new ProductCustomException("INSUFFICIENT_QUANTITY");
			}

			findByItem.setQuantity(findByItem.getQuantity() - requestOrder.getQuantity());

			return OrderRepo.save(orders);

		}

		catch (Exception e) {
			throw new ProductCustomException("Invalid " + e.getMessage());
		}
	}

	public void cancelOrder(long id) throws Exception {
		try {
			Orders findById = OrderRepo.findById(id).orElseThrow(() -> new IdNotFoundException("order not found"));

			long cartId = findById.getCart().getCartId();

			Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IdNotFoundException("cart not found"));

			List<Long> map = cart.getItem().stream().map(e -> e.getItemId()).toList();

			Items item = new Items();
			RequestItems req = new RequestItems();

			item.setQuantity(item.getQuantity() + req.getQuantity());

			OrderRepo.deleteById(id);
		} catch (IdNotFoundException idNotFoundException) {
			throw new IdNotFoundException("Id not found" + idNotFoundException);
		} catch (Exception exception) {
			throw new Exception(exception);
		}
	}

	public Orders updateOrder(Orders order, long id) {

		return OrderRepo.save(order);
	}

	public List<Orders> getAllOrders() {

		return OrderRepo.findAll();
	}

	public Orders getOrderDetails(long orderId) throws Exception {

		 
		try {
			Orders order = OrderRepo.findById(orderId).get();
			return order;
		}

		catch (OrderCustomException e) {

			throw new OrderCustomException("Order not found for the order Id:" + orderId,

					HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.value());
		}

		catch (Exception e) {

			throw new NotBoundException(e.getMessage());
		}

		 
	}




}
