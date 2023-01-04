package com.wdm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "wholesale")
public class WholesaleEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long wholesaleId;
	
	private String name;
	
	
	@ManyToMany(mappedBy = "wholesale")
	
	private List<SuperMarketEntity> supermarket;

	
	public WholesaleEntity()
	{
		
	}
	
	
	public WholesaleEntity(String name, List<SuperMarketEntity> supermarket) {
		super();
		
		this.name = name;
		this.supermarket = supermarket;
	}


	public long getWholesaleId() {
		return wholesaleId;
	}


	public void setWholesaleId(long wholesaleId) {
		this.wholesaleId = wholesaleId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<SuperMarketEntity> getSupermarket() {
		return supermarket;
	}


	public void setSupermarket(List<SuperMarketEntity> supermarket) {
		this.supermarket = supermarket;
	}


	@Override
	public String toString() {
		return "WholesaleEntity [wholesaleId=" + wholesaleId + ", name=" + name + ", supermarket=" + supermarket + "]";
	}
	
	
}
