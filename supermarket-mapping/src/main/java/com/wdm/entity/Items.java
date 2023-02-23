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
@Table
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;

	private int quantity;

	@Column(name="total_price")
	private double totalPrice;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_Id", referencedColumnName = "product_Id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	@JsonIgnore
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

	 

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

//	@Override
//	public String toString() {
//		return "Items [itemId=" + itemId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", product="
//				+ product + "]";
//	}
	
	
	
	 

}
