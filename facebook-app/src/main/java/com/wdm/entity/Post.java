package com.wdm.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {
	
	@Id
	@Column(name = "postId")
	private String postId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "user_id")
	private User user;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mediaLocation", referencedColumnName = "media_id")
	private MediaLocation mediaLocation;
	
	@Column(name = "crratedDateTime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date crratedDateTime;

	
	
	
	
	
	
	
	
	public MediaLocation getMediaLocation() {
		return mediaLocation;
	}

	public void setMediaLocation(MediaLocation mediaLocation) {
		this.mediaLocation = mediaLocation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	 

	public Date getCrratedDateTime() {
		return crratedDateTime;
	}

	public void setCrratedDateTime(Date crratedDateTime) {
		this.crratedDateTime = crratedDateTime;
	}

	public Post(String postId, User user, String description, MediaLocation mediaLocation, Date crratedDateTime) {
		super();
		this.postId = postId;
		this.user = user;
		this.description = description;
		this.mediaLocation = mediaLocation;
		this.crratedDateTime = crratedDateTime;
	}
	
	
	public Post()
	{
		
	}
	
	
	

}
