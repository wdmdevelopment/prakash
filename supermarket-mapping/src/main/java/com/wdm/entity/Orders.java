package com.wdm.entity;

 

import java.time.LocalDateTime;

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
	
	 
	
	@Column(name = "ordertime")
	private LocalDateTime ordertime;
	
	@Column(name = "total_Amount")
	private long totalAmount;
	
	
	 
	
	@OneToOne
	@JoinColumn(name = "cartId",referencedColumnName = "cartId")
	private Cart cart;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "userId")
	 @JsonIgnore
	 private UserAccount user;
	 
	 
	 
	 
	
	
public long getOrderId() {
		return orderId;
	}






	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}





 





	public LocalDateTime getOrdertime() {
		return ordertime;
	}






	public void setOrdertime(LocalDateTime ordertime) {
		this.ordertime = ordertime;
	}






	public Cart getCart() {
		return cart;
	}






	public void setCart(Cart cart) {
		this.cart = cart;
	}






	public UserAccount getUser() {
		return user;
	}






	public void setUser(UserAccount user) {
		this.user = user;
	}






public long getTotalAmount() {
		return totalAmount;
	}






	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}





 





public Orders() {
		
	}
	

}
