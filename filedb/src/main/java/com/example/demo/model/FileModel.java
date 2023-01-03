package com.example.demo.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@Table(name="fileuploadig")
public class FileModel {

	@Id
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String type;
	
	@Lob
	
	private Blob data;
	
	public FileModel() {
		
	}
	
	
	public FileModel(String id, String name, String type, Blob data) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.data = data;
	}


	

	@Override
	public String toString() {
		return "FileModel [id=" + id + ", name=" + name + ", type=" + type + ", data=" + data + "]";
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Blob getData() {
		return data;
	}


	public void setData(Blob data) {
		this.data = data;
	}

	
	

}
