package com.med.firstapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="orders")
public class Order {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@Column(name="orderNumber", nullable=false)
	//Validation
	@NotNull
	private Integer number;

	//Mapping
	@Column(name="orderDate", nullable=false)
	//Validation
	@NotNull
	private Date orderDate;
	
	//Mapping
	@Column(name="requiredDate", nullable=false)
	//Validation
	@NotNull
	private Date requiredDate;

	//Mapping
	@Column(name="shippedDate")
	//Validation
	private Date shippedDate;
	
	//Mapping
	@Column(name="status", nullable=false)
	//Validation
	@NotEmpty @Size(max =17)
	private String status;
	
	//Mapping
	@Column(name="comments")
	//Validation
	private String comments;
	
	//Mapping
	@ManyToOne(optional=false)
	@JoinColumn(name="customerId")
	//Validation
	@NotNull
	private Customer customer;

	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable=true)
    private List<OrderDetails> orderDetails;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date date) {
		this.orderDate = date;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
