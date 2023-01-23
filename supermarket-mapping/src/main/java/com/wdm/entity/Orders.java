package com.wdm.entity;

 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Orders")

public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private long orderId;
	
	@Column(name = "totalprice")
	private double totalPrice;
	
	@Column(name = "ordertime")
	private String ordertime;
	
	@Column(name = "STATUS")
    private String orderStatus;
	
	@OneToOne
	@JoinColumn(name = "cartId",referencedColumnName = "cartId")
	private Cart cart;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "userId")
	 @JsonIgnore
	 private UserAccount user;
	 
	 
	 
	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}
	
	
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	

	public Orders(long orderId, double totalPrice, String ordertime, Cart cart, UserAccount user) {
		super();
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.ordertime = ordertime;
		this.cart = cart;
		this.user = user;
		
	}

	
	public Orders() {
		
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	
	

}
