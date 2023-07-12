package com.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userId;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="user_name")
	private String userName;

	@Column(name="status")
	private String status;
	
	@Column(unique = true, nullable = false, name="email_id")
	private String emailId;
	
	@Column(length = 1000, name="picture")
	private String picture;
	
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	 
	private String role;
	 
	
	
	
	
}
