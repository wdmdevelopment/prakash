package com.wdm.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;

	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	private Set<Items> item = new HashSet<>();

	
	@OneToOne
	@JoinColumn(name = "user", referencedColumnName = "userId")
	private UserAccount user;
	
	private String OrderStatus;
	
	
	private double totalAmount;
	
	
	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	

	public Set<Items> getItem() {
		return item;
	}

	public void setItem(Set<Items> item) {
		this.item = item;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	 




}
