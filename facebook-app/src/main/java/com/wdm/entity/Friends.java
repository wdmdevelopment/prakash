package com.wdm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Friends")
public class Friends {
	
	@Id
	@Column(name = "friendsId")
	private String friendId;
	
//	@ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
//	@JoinColumn(name = "requestUser", referencedColumnName = "user_id")
//	private User requestUser;
//	
//	@ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
//	@JoinColumn(name = "acceptUser", referencedColumnName = "user_id")
//	private User acceptUser;
	
	@Column(name = "requeststatus")
	private String RequestStatus;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	 

	 

	public String getRequestStatus() {
		return RequestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}

	public Friends(String friendId, String requestStatus) {
		super();
		this.friendId = friendId;
		 
		RequestStatus = requestStatus;
	}
	
	public Friends() {
		
	}
	

}
