package com.wdm.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Share")
public class PostShare {
	
	@Id
	@Column(name = "shareId")
	private String shareId;
	
	@ManyToMany
	private User sendUser;
	
	@ManyToMany
	private User receiveUser;
	
	private Date sharingTime;

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public User getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}

	public Date getSharingTime() {
		return sharingTime;
	}

	public void setSharingTime(Date sharingTime) {
		this.sharingTime = sharingTime;
	}

	public PostShare(String shareId, User sendUser, User receiveUser, Date sharingTime) {
		super();
		this.shareId = shareId;
		this.sendUser = sendUser;
		this.receiveUser = receiveUser;
		this.sharingTime = sharingTime;
	}
	
	
	public PostShare() {
		
	}
	
	

}
