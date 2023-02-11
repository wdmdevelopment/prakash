package com.wdm.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Post")
public class PostEntity {
	
	@Id
	@Column(name = "postId")
	private String postId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "mediaLocation")
	private String mediaLocation;
	
	@Column(name = "crratedDateTime")
	private Date crratedDateTime;

	
	
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaLocation() {
		return mediaLocation;
	}

	public void setMediaLocation(String mediaLocation) {
		this.mediaLocation = mediaLocation;
	}

	public Date getCrratedDateTime() {
		return crratedDateTime;
	}

	public void setCrratedDateTime(Date crratedDateTime) {
		this.crratedDateTime = crratedDateTime;
	}

	public PostEntity(String postId, User user, String description, String mediaLocation, Date crratedDateTime) {
		super();
		this.postId = postId;
		this.user = user;
		this.description = description;
		this.mediaLocation = mediaLocation;
		this.crratedDateTime = crratedDateTime;
	}
	
	
	
	
	
	

}
