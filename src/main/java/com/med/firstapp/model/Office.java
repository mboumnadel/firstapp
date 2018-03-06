package com.med.firstapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="offices")
public class Office {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@Column(nullable = false)
	//Validation
	@NotEmpty
	@Size(max = 50)
	private String city;

	//Mapping
	@Column(name="phone", nullable = false)
	//Validation
	@Size(min=3, max=20)
	private String phoneNumber;

	//Mapping
	@Column(nullable = false)
	//Validation
	@NotEmpty
	@Size(max = 50)
	private String addressLine1;

	//Mapping
	@Column(nullable = true)
	//Validation
	@Size(max = 50)
	private String addressLine2;

	//Mapping
	@Column(nullable = true)
	//Validation
	@Size(max = 50)
	private String state;

	//Mapping
	@Column(nullable = false)
	//Validation
	@NotEmpty
	@Size(max = 50)
	private String country;

	//Mapping
	@Column(nullable = false)
	//Validation
	@NotEmpty
	@Size(max = 15)
	private String postalCode;

	//Mapping
	@Column(nullable = false)
	//Validation
	@NotEmpty
	@Size(max = 10)
	private String territory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

}
