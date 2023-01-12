package com.wdm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
 
import javax.persistence.OneToMany;
import javax.persistence.Table;
 

@Entity
@Table(name = "Catagory")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long categoryId;
	
	@Column(name = "name")
	private String categoryName;
	
	
	
	@OneToMany(mappedBy = "category")
	
	private List<Product> product;

	
	
	
	
	
	
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	 

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Category(long categoryId, String categoryName, List<Product> product) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.product = product;
	}
	
	
	public Category() {
		
	}
	
	
	
	

}
