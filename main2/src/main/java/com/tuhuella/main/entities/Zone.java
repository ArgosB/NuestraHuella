package com.tuhuella.main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tuhuella.main.enums.Province;

@Entity 
public class Zone {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 50)
	private String id;

	@Column(length = 50)
	private String country;
	@Column(length = 50)
	private Province province;
	@Column(length = 50)
	private String city;
	@Column(length = 50)
	private String neighborhood;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}			


	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public Zone() {
		super();
	}
	
}
