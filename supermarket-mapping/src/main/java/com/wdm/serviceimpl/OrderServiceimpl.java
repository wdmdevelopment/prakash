package com.wdm.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wdm.entity.Cart;
import com.wdm.entity.Items;
import com.wdm.entity.Orders;
import com.wdm.entity.Product;
import com.wdm.entity.UserAccount;
import com.wdm.exception.IdNotFoundException;
import com.wdm.exception.OrderCustomException;
import com.wdm.exception.ProductCustomException;
import com.wdm.model.RequestItems;
import com.wdm.model.RequestOrder;
import com.wdm.repository.CartRepository;
import com.wdm.repository.ItemsRepository;
import com.wdm.repository.OrderRepository;
import com.wdm.repository.ProductMappingRespository;
import com.wdm.repository.UserAccountRespository;
import com.wdm.response.OrderResponse;
import com.wdm.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService {

	@Autowired
	OrderRepository OrderRepo;
	
	@Autowired
	UserAccountRespository userRepo;
	
	@Autowired
	ProductMappingRespository productRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	ItemsRepository itemRepo;

	
	public Orders placeOrder(RequestOrder requestOrder, long userId) {
		
		UserAccount findById = userRepo.findById(userId).orElseThrow(() -> new IdNotFoundException(userId+" Not Found "));


		try {
			
			
		String getuserRoll = findById.getuserRoll();
		Orders orders = new Orders();
			 
		if(getuserRoll.equalsIgnoreCase("customer") || getuserRoll.equalsIgnoreCase("admin")) {
			
			Cart findBycart = cartRepo.findById(userId).orElseThrow(() -> new IdNotFoundException(userId+" Not Found "));
	
			Items findByItem = itemRepo.findById(userId).orElseThrow(() -> new IdNotFoundException(userId+" Not Found "));
			
			Product findByproduct = productRepo.findById(userId).orElseThrow(() -> new IdNotFoundException(userId+" Not Found "));
			
			orders.setCart(findBycart);
			
			findBycart.setItem((List<Items>) findByItem);
			
			findByItem.setProduct(findByproduct);
			
		 
		 
		orders.setTotalPrice(requestOrder.getTotalPrice());
		
		orders.setOrdertime(requestOrder.getOrdertime());
		
		orders.setOrderStatus(requestOrder.getOrderStatus());
		
		  
		if(findByItem.getQuantity() < requestOrder.getQuantity()) {
            throw new ProductCustomException("INSUFFICIENT_QUANTITY");
        }

		findByItem.setQuantity(findByItem.getQuantity() - requestOrder.getQuantity());
		}
		
		return  OrderRepo.save(orders);
		
		}
		
		catch (Exception e) {
			throw new ProductCustomException("Invalid "+e.getMessage());
		}
	}

	public void cancelOrder(long id) throws Exception {
		try {
			Orders findById = OrderRepo.findById(id).orElseThrow(() -> new IdNotFoundException("order not found"));
			
			long cartId = findById.getCart().getCartId();
			
			Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IdNotFoundException("cart not found"));
			
			Stream<Object> map = cart.getItem().stream().map(e -> e.getQuantity());
			
			
			Items item = new Items();
			RequestItems req = new RequestItems();
			
			item.setQuantity(item.getQuantity() + req.getQuantity());
			
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
