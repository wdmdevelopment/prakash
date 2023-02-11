package com.wdm.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "postLike")
public class PostLike {
	
	@Id
	@Column(name = "likeId")
	private String likeId;
	
	 @OneToOne
	 private PostEntity post;
	 
	 @ManyToOne
	 private User user;
	 
	 private Date liketime;

	public String getLikeId() {
		return likeId;
	}

	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLiketime() {
		return liketime;
	}

	public void setLiketime(Date liketime) {
		this.liketime = liketime;
	}

	public PostLike(String likeId, PostEntity post, User user, Date liketime) {
		super();
		this.likeId = likeId;
		this.post = post;
		this.user = user;
		this.liketime = liketime;
	}
	 
	 
	public PostLike() {
		
	}
	
	

}
