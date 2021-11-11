package com.tuhuella.main.entities;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Photo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", length = 50)
	private String id;
	@Column(name = "name", length = 50)
	private String name;
	/*@Column(name = "mime"length = 50)
	private String mime;*/
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "picture", length = 50)
	private byte[] picture;
	@Column(name = "created", length = 50)
	private Date createPhoto;
	@Column(name = "modified", length = 50)
	private Date modifiedPhoto;
	@Column(name = "isActive",length = 50)
	private Boolean active;
	
	public Photo() {
		
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

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Date getCreatePhoto() {
		return createPhoto;
	}

	public void setCreatePhoto(Date createPhoto) {
		this.createPhoto = createPhoto;
	}

	public Date getModifiedPhoto() {
		return modifiedPhoto;
	}

	public void setModifiedPhoto(Date modifiedPhoto) {
		this.modifiedPhoto = modifiedPhoto;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + ", picture=" + Arrays.toString(picture) + ", createPhoto="
				+ createPhoto + ", modifiedPhoto=" + modifiedPhoto + ", active=" + active + "]";
	}
	
	
	

}
