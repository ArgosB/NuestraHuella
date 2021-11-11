package com.tuhuella.main.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Zone {
	@Id
	private String id;
	private String province;
	private String city;
	private String neighborhood;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}			
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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
	public Zone() {
		super();
	}
	
}
