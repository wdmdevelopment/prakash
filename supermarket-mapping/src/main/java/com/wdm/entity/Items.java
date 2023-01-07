package com.wdm.entity;

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
@Table
public class Items {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long itemId;
	
	 
	private int quantity;
	
	private double price;
	
	@OneToOne
	@JoinColumn(name = "product",referencedColumnName = "product_Id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "itemId", insertable=false, updatable=false)
	//@JsonIgnore
	private Cart cart;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
