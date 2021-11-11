package com.tuhuella.main.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User {
	@Id
    @Column(name = "id_user", nullable = false, unique = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@OneToOne
	private Photo photo;
	@Column(length = 50)
	private String name;
	@Column(length = 50)
	private String surname;
	@Column(length = 50, unique = true )
	private String username;
	@Column(length = 50)
	private String password;
	@Column
	private boolean enabled;
    @Column(length = 50)
	private Date BirthDate;
	@Column(length = 50)
	private String street;
	@Column(length = 50)
	private Integer streetNumber;
	@ManyToOne
	private Zone zone;
	@Column(length = 50)
	private Integer phoneNumber;
	@Column(length = 50)
	private Integer alternativeNumber;
	@Column(length = 50)
	private String email;
	@Temporal(TemporalType.DATE)
	@Column(length = 50)
	private Date createUser;
	@Temporal(TemporalType.DATE)
	@Column(length = 50)
	private Date modifiedUser;
	@Column(length = 50)
	private Boolean active;
	
	public User() {
		super();
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	public Date getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getAlternativeNumber() {
		return alternativeNumber;
	}
	public void setAlternativeNumber(Integer alternativeNumber) {
		this.alternativeNumber = alternativeNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Date createUser) {
		this.createUser = createUser;
	}
	public Date getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(Date modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "authorities_users", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authority;
	
	
	public Set<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	public boolean isEnabled() {
		return enabled;
	}
	public User(String username, String password, Set<Authority> authority) {
		super();
		this.username = username;
		this.password = password;
		this.authority = authority;
	}
	

}

	

