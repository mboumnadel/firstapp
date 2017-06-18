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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="orderdetails")
public class OrderDetails {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@ManyToOne
	@JoinColumn(name="orderId", nullable=false)
	//Validation
	@NotNull
	private Order order;

	//Mapping
	@ManyToOne
	@JoinColumn(name="productId", nullable=false)
	//Validation
	@NotNull
	private Product product;
	
	//Mapping
	@Column(name = "quantityOrdered", nullable = false)
	//Validation
	@NotNull
	private Integer quantityOrdered;

	//Mapping
	@Column(name = "priceEach", nullable = false)
	//Validation
	@NotNull
	private BigDecimal priceEach;

	//Mapping
	@Column(name = "orderLineNumber", nullable = false)
	//Validation
	@NotNull
	private Integer orderLineNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", order=" + order + ", product=" + product + ", quantityOrdered="
				+ quantityOrdered + ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + "]";
	}
	
}
