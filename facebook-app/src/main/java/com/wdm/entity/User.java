package com.wdm.entity;

 
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "first_name")
	private String fisrtName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "date_of_birth")
	private LocalDate dob;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_id")
	private String email;
	
	@Column(name = "phone_no")
	private long phoneNo;
	
	@Column(name = "about")
	private String about;
	
	 
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> post;
	
	
	
 
	
	
	
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	 

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	 
 
	
	
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public User(String userId, String fisrtName, String lastName, LocalDate dob, String password, String email,
			long phoneNo, String about, List<Post> post) {
		super();
		this.userId = userId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.dob = dob;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.about = about;
		this.post = post;
	}

	public User()
	{
		
	}
	
	
	

}
