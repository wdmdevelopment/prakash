package com.wdm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity
@Table(name = "User")

public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	private String firstName;

	private String lastName;
	 
	 
	private String emailId;

	private String password;

	private String userRole;

	@OneToMany(mappedBy = "user")
	 @JsonIgnore
	private List<Orders> order;

	
	public UserAccount() {
		
	}
	
	
	
	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getuserRoll() {
		return userRole;
	}

	public void setuserRoll(String userRole) {
		this.userRole = userRole;
	}

	public UserAccount(long userId, String firstName, String lastName, String emailId, String password, String userRole,
			List<Orders> order) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.userRole = userRole;
		this.order = order;
	}

}
