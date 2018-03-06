package com.med.firstapp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="customers")
public class Customer {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@Column(name="customerNumber", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String number;

	//Mapping
	@Column(name="customerName", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String name;

	//Mapping
	@Column(name="contactLastName", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String contactCastName;

	//Mapping
	@Column(name="contactFirstName", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String contactFirstName;

	//Mapping
	@Column(name="phone", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String phoneNumber;

	//Mapping
	@Column(name="addressLine1", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String addressLine1;


	//Mapping
	@Column(name="addressLine2")
	//Validation
	@Size(max = 50)
	private String addressLine2;

	//Mapping
	@Column(name="city", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String city;

	//Mapping
	@Column(name="state")
	//Validation
	@Size(max = 50)
	private String state;

	//Mapping
	@Column(name="postalCode")
	//Validation
	@Size(max = 15)
	private String postalCode;

	//Mapping
	@Column(name="country", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String country;

	//Mapping
	@ManyToOne(optional=true)
	@JoinColumn(name="salesRepEmployeeId")
	//Validation
	private Employee salesRep;

	//Mapping
	@Column(name="creditLimit", nullable=true)
	//Validation
	private BigDecimal creditLimit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return contactCastName;
	}

	public void setLastName(String lastName) {
		this.contactCastName = lastName;
	}

	public String getFirstName() {
		return contactFirstName;
	}

	public void setFirstName(String firstName) {
		this.contactFirstName = firstName;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employee getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(Employee salesRep) {
		this.salesRep = salesRep;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
}
