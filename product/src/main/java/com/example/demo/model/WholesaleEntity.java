package com.example.demo.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "wholesale")
public class WholesaleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int wholesaleId;
	
	private String name;
	
	
	@ManyToMany
	@JsonIgnore
	private List<SuperModelEntity> supermarket;

	
	public WholesaleEntity()
	{
		
	}
	
	
	public WholesaleEntity(int wholesaleId, String name, List<SuperModelEntity> supermarket) {
		super();
		this.wholesaleId = wholesaleId;
		this.name = name;
		this.supermarket = supermarket;
	}


	public int getWholesaleId() {
		return wholesaleId;
	}


	public void setWholesaleId(int wholesaleId) {
		this.wholesaleId = wholesaleId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<SuperModelEntity> getSupermarket() {
		return supermarket;
	}


	public void setSupermarket(List<SuperModelEntity> supermarket) {
		this.supermarket = supermarket;
	}


	@Override
	public String toString() {
		return "WholesaleEntity [wholesaleId=" + wholesaleId + ", name=" + name + ", supermarket=" + supermarket + "]";
	}
	
	
	
	

}
