package com.rentalapp.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "propertymessage")
public class PropertyMessages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "fromuserid")
	private User from;
	
	@ManyToOne
    @JoinColumn(name = "touserid")
	private User to;
	
	private LocalDateTime time;
	
	@Column(length = 1000)
	private String Message;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE ,CascadeType.REMOVE})
	@JoinColumn(name = "propertyId", referencedColumnName = "id")
	private Property property;
}
