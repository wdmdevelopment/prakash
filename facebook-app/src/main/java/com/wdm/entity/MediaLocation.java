package com.wdm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "media_location")
public class MediaLocation {

	@Id
	@Column(name = "media_id")
	private String mediaId;
	
	@Column(name = "media_path")
	private String mediaPath;

	public MediaLocation(String mediaId, String mediaPath) {
		super();
		this.mediaId = mediaId;
		this.mediaPath = mediaPath;
	}
	public MediaLocation()
	{
		
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
	
	
	
	
	
}
