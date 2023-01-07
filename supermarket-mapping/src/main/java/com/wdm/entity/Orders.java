package com.wdm.entity;

import java.time.LocalDateTime;

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
	private LocalDateTime ordertime;
	
	
	@OneToOne
	@JoinColumn(name = "Cart",referencedColumnName = "cartId")
	private Cart cart;
	
	 @ManyToOne
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

	public LocalDateTime getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(LocalDateTime ordertime) {
		this.ordertime = ordertime;
	}

	

	public Orders(long orderId, double totalPrice, LocalDateTime ordertime, Cart cart, UserAccount user) {
		super();
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.ordertime = ordertime;
		this.cart = cart;
		this.user = user;
		
	}

	
	public Orders() {
		
	}
	
	
	
	
	

}
