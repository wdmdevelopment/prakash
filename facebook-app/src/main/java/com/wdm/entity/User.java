package com.wdm.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private Date dob;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_id")
	private String email;
	
	@Column(name = "phone_no")
	private long phoneNo;
	
	@Column(name = "about")
	private String about;
	
	@Column(name = "one_time_password")
	private String ontimePassword;
	
	@Column(name = "otp_request_time")
	private Date otpRequestedTime;

	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private PostEntity postentity;
	
	
	
	
	
	
	
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public String getOntimePassword() {
		return ontimePassword;
	}

	public void setOntimePassword(String ontimePassword) {
		this.ontimePassword = ontimePassword;
	}

	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	public User(String userId, String fisrtName, String lastName, Date dob, String password, String email, long phoneNo,
			String about, String ontimePassword, Date otpRequestedTime) {
		super();
		this.userId = userId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.dob = dob;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.about = about;
		this.ontimePassword = ontimePassword;
		this.otpRequestedTime = otpRequestedTime;
	}
	
	
	
	public User()
	{
		
	}
	
	
	

}
