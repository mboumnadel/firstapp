package com.med.firstapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="employees")
public class Employee {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@Column(name = "employeeNumber", nullable = false)
	//Validation
	@NotEmpty
	private String number;
	
	//Mapping
	@Column(nullable = false)
	//Validation
	@Size(min=3, max=20)
	private String firstName;

	//Mapping
	@Column(nullable = false)
	//Validation
	@Size(min=3, max=20)
	private String lastName;
	
	//Mapping
	@Column(nullable = false)
	//Validation
	@Size(min=2, max=5) @NotEmpty
	private String extension;
	
	//Mapping
	//Validation
	@Email @NotEmpty
	private String email;
	
	//Mapping
	@ManyToOne(optional=false)
	@JoinColumn(name="officeId")
	//Validation
	private Office office;
	
	//Mapping
	@OneToOne(optional=true)
	@JoinColumn(name="reportsTo")
	//Validation
	//@NotNull
	private Employee reportsTo;

	//Mapping
	@Column(nullable = false)
	//Validation
	@NotEmpty
	private String jobTitle;
	
	//Mapping
	@Transient
	//Validation
	@DateTimeFormat(pattern="dd/MM/yyyy")
	//@Past @NotNull
	private Date hireDate;
	

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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Employee getReportsTo() {
		return reportsTo;
	}
	
	public void setReportsTo(Employee reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "Employee [number=" + number + ", firstName=" + firstName + ", lastName=" + lastName + ", extension="
				+ extension + ", email=" + email + ", office=" + office.getId() + ", reportsTo=" + ((reportsTo == null)?"":reportsTo.getId())
				+ ", jobTitle=" + jobTitle + ", hireDate=" + hireDate + "]";
	}

}
