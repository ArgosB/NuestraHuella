package com.tuhuella.main.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tuhuella.main.enums.Sex;
import com.tuhuella.main.enums.Size;
import com.tuhuella.main.enums.Species;

@Entity
@Table(name = "pet")
public class PetUser extends ModelUser {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 50)
	private String id;
	@OneToOne
	private Photo photo;
	@Column(length = 50)
	private String name;
	@Column(length = 50)
	private Integer ageInMonths;
	@Column(length = 50)
	private Species species;
	@Column(length = 50)
	private String breed;
	@Column(length = 50)
	private Integer weight;
	@Column(length = 50)
	private Sex sex;
	@Column(length = 50)
	private Size size;
	private Boolean upToDateVaccine;
	private Boolean castrated;
	private Boolean dewormed;
	@Column(length = 50)
	private String disease;
	@ManyToOne
	private HumanUser User;
	@ManyToOne
	private Zone zone;

	public PetUser() {
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAgeInMonths() {
		return ageInMonths;
	}
	public void setAgeInMonths(Integer ageInMonths) {
		this.ageInMonths = ageInMonths;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
		}
	public Boolean getUpToDateVaccine() {
		return upToDateVaccine;
	}
	public void setUpToDateVaccine(Boolean upToDateVaccine) {
		this.upToDateVaccine = upToDateVaccine;
	}
	public Boolean getCastrated() {
		return castrated;
	}
	public void setCastrated(Boolean castrated) {
		this.castrated = castrated;
	}
	public Boolean getDewormed() {
		return dewormed;
	}
	public void setDewormed(Boolean dewormed) {
		this.dewormed = dewormed;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public HumanUser getUser() {
		return User;
	}

	public void setUser(HumanUser user) {
		User = user;
	}
}
