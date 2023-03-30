package com.wdm.serviceimpl;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.wdm.model.ResponseOrder;
import com.wdm.repository.CartRepository;
import com.wdm.repository.ItemsRepository;
import com.wdm.repository.OrderRepository;
import com.wdm.repository.ProductMappingRespository;
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

	@Autowired
	ProductMappingRespository productRepo;

	public Orders placeOrder(RequestOrder requestOrder) {

		Orders orders = new Orders();
		try {

			Cart findBycart = cartRepo.findById(requestOrder.getCartId())
					.orElseThrow(() -> new IdNotFoundException(requestOrder.getCartId() + " Not Found "));

			findBycart.setOrderStatus("INACTIVE");

			orders.setCart(findBycart);

			UserAccount userAccount = userRepo.findById(requestOrder.getUserId())
					.orElseThrow(() -> new IdNotFoundException("user id not found"));

			orders.setUser(userAccount);

			orders.setTotalAmount(findBycart.getTotalAmount());

			List<Items> list = itemRepo.findByCart_CartId(requestOrder.getCartId());

			for (Items item : list) {

				if (item.getProduct().getStocks() > item.getQuantity()) {
					item.getProduct().setStocks(item.getProduct().getStocks() - item.getQuantity());
				} else {

					throw new ProductCustomException("INSUFFICIENT QUANTITY");

				}

			}

			return OrderRepo.save(orders);

		}

		catch (Exception e) {
			e.printStackTrace();
			throw new ProductCustomException(e.getMessage());
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

	public List<Orders> getAllOrders(long userId) {

		List<Orders> user_UserId = OrderRepo.findByUser_UserId(userId);
		
		return user_UserId;
	}

	//public List<ResponseOrder> getOrders(long userId) {
//
//		List<ResponseOrder> findByUser_UserId = OrderRepo.findByUser_UserId(userId).stream()
//				.filter(e -> new ResponseOrder(e.getDateTime(), e.getTotalAmount(),
//						e.getCart().getItem().stream().filter(e -> e.getProduct().getProductName()).collect(
//								Collectors.toList()),
//						e.getCart().getItem().stream().filter(e -> e.getQuantity()),
//						e.getCart().getItem().stream().filter(e -> e.getTotalPrice()),
//						e.getCart().getItem().stream().filter(e -> e.getProduct().getPrice()).toList(),
//						e.getCart().getItem().stream().filter(e -> e.getProduct().getProductImage().getImageData()))).
//				collect(Collectors.toList());
//
//		return null;

//		List<ResponseOrder> responseOrder=new ArrayList<>();
//		for(Orders o:findByUser_UserId)
//		{
//			ResponseOrder resOrder = new ResponseOrder();
//			resOrder.setDateTime(o.getDateTime());
//			
//			resOrder.setImageData(o.getCart().getItem().stream().map(e -> e.getProduct().getProductImage()
//		.getImageData()).collect(Collectors.toList()));
//			
//		}

	//}

	public List<Cart> getAllorderInActive(long userId) {

		return cartRepo.findByOrderStatusUser(userId, "INACTIVE");
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
