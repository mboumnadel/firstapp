package com.med.firstapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="vehicle")
public class Vehicle {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@Column(name="year", nullable=false)
	//Validation
	@NotNull @Min(1900)
	private Integer year;

	//Mapping
	@Column(name="make")
	//Validation
	@Size(max = 50)
	private String make;

	//Mapping
	@Column(name="model", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String model;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
