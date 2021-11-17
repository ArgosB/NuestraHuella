package com.tuhuella.main.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class ModelUser  {
	@Id
    @Column(name = "id_user", nullable = false, unique = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@OneToOne
	private Photo photo;
	@Column(length = 50)
	private String name;
	@ManyToOne
	private Zone zone;
	
}
