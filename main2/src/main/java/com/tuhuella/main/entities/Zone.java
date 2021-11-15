package com.tuhuella.main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.tuhuella.main.enums.Province;

@Entity 
public class Zone {
	@Id
	private String id;

	@Column(length = 50)
	private String country;
	@Column(length = 50)
	private Province province;
	@Column(length = 50)
	private String city;
	@Column(length = 50)
	private String neighborhood;
	@OneToOne
	private HumanUser user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}			

	public HumanUser getUser() {
		return user;
	}
	public void setUser(HumanUser user) {
		this.user = user;
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
