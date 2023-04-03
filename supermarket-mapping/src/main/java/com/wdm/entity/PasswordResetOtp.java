package com.wdm.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 

@Entity
@Table(name = "ResetPassword")
public class PasswordResetOtp {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	
	 
	 	private String oneTimePassword;
	 	
	 	private LocalDateTime expiryTime;
	 	
	 	private String email;

		
//	 	@OneToOne(cascade = CascadeType.ALL)
//	  
//	 	private UserAccount user;
//	 	
//	 	
//	 	public UserAccount getUser() {
//			return user;
//		}
//
//		public void setUser(UserAccount user) {
//			this.user = user;
//		}

		 

		public void setId(long id) {
			this.id = id;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getOneTimePassword() {
			return oneTimePassword;
		}

		public void setOneTimePassword(String oneTimePassword) {
			this.oneTimePassword = oneTimePassword;
		}

		public LocalDateTime getExpiryTime() {
			return expiryTime;
		}

		public void setExpiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	 	
	 	
	 	
	 	

}
