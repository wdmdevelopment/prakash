package com.wdm.model;
  
 
import javax.validation.constraints.NotNull;
 
public class RequestItems {
	
	
	@NotNull
	private int quantity;
	
	@NotNull
	private long productId;
	
	@NotNull
	private long userId;
	
	@NotNull
	private long cartId;
	
	
	
	
	
	 
	 

	public long getCartId() {
		return cartId;
	}


	public void setCartId(long cartId) {
		this.cartId = cartId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	 

	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	 
	
	


}
