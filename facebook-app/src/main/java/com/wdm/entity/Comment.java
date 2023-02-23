package com.wdm.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Postcomment")
public class Comment {
	
	@Id
	@Column(name = "commentId")
	private String commentId;
	
	@OneToOne
	private Post post;
	
	@ManyToOne
	private User user;
	
	@Column(name = "commentText")
	private String commentText;
	
	@Column(name = "commentTime")
	private Date commentTime;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Comment(String commentId, Post post, User user, String commentText, Date commentTime) {
		super();
		this.commentId = commentId;
		this.post = post;
		this.user = user;
		this.commentText = commentText;
		this.commentTime = commentTime;
	}
	
	
	public Comment() {
		
	}
	
	

}
