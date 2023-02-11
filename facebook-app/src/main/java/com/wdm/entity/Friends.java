package com.wdm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Friends")
public class Friends {
	
	@Id
	@Column(name = "friendsId")
	private String friendId;
	
	@ManyToMany
	@JoinColumn(name = "requestUser", referencedColumnName = "requestUserId")
	private User requestUser;
	
	@ManyToMany
	@JoinColumn(name = "acceptUser", referencedColumnName = "acceptUserId")
	private User acceptUser;
	
	@Column(name = "requeststatus")
	private String RequestStatus;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public User getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(User requestUser) {
		this.requestUser = requestUser;
	}

	public User getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(User acceptUser) {
		this.acceptUser = acceptUser;
	}

	public String getRequestStatus() {
		return RequestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}

	public Friends(String friendId, User requestUser, User acceptUser, String requestStatus) {
		super();
		this.friendId = friendId;
		this.requestUser = requestUser;
		this.acceptUser = acceptUser;
		RequestStatus = requestStatus;
	}
	
	public Friends() {
		
	}
	

}
