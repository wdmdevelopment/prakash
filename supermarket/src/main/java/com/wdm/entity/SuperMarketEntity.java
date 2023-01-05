package com.wdm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="supermarket")
public class SuperMarketEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long superId;
		
	private String superMarketName;
	
	@OneToOne
	@JoinColumn(name = "address",referencedColumnName = "ADDRESS_ID")
	private Address address;
	
	
	 @OneToMany(mappedBy = "superModelEnitity")
	
	 private List<ProductEntity> product;
	 
	
	
	
	@ManyToMany 
	@JoinTable(joinColumns = {@JoinColumn(name = "superId")},inverseJoinColumns = {@JoinColumn(name = "wholesaleId")
        })
	
	private List<WholesaleEntity> wholesale;


	

	public SuperMarketEntity(String superMarketName, Address address, List<ProductEntity> product,
			List<WholesaleEntity> wholesale) {
		super();
		this.superMarketName = superMarketName;
		this.address = address;
		this.product = product;
		this.wholesale = wholesale;
	}


	public SuperMarketEntity() {
		
	}


	public long getSuperId() {
		return superId;
	}


	public List<ProductEntity> getProduct() {
		return product;
	}


	public void setProduct(List<ProductEntity> product) {
		this.product = product;
	}


	public void setSuperId(long superId) {
		this.superId = superId;
	}


	public String getSuperMarketName() {
		return superMarketName;
	}


	public void setSuperMarketName(String superMarketName) {
		this.superMarketName = superMarketName;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<WholesaleEntity> getWholesale() {
		return wholesale;
	}


	public void setWholesale(List<WholesaleEntity> wholesale) {
		this.wholesale = wholesale;
	}


	@Override
	public String toString() {
		return "SuperModelEntity [superId=" + superId + ", superMarketName=" + superMarketName + ", address=" + address
				+ ", wholesale=" + wholesale + "]";
	}
	
}
