package com.med.firstapp.model;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="vehicle")
@EntityListeners(AuditingEntityListener.class)
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

	@Column(name = "created_at", nullable = false, updatable =  false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "created_by", nullable = false, updatable =  false)
	@CreatedBy
	private String createdBy;

	@Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

	@Column(name = "version")
	@Version
	//javax.persistence.Version
	private Long version;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Vehicle(){
	}

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

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", year=" + year + ", make=" + make + ", model=" + model + "]";
	}

}
