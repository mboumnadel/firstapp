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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="products")
public class Product {

	//Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//Mapping
	@Column(name="productCode", nullable=false)
	//Validation
	@NotEmpty @Size(max = 15)
	private String code;
	
	//Mapping
	@Column(name="productName", nullable=false)
	//Validation
	@NotEmpty @Size(max = 70)
	private String name;
	
	//Mapping
	@ManyToOne(optional=false)
	@JoinColumn(name="productLineId")
	//Validation
	@NotNull
	private ProductLine productLine;

	//Mapping
	@Column(name="productScale", nullable=false)
	//Validation
	@NotEmpty @Size(max = 10)
	private String scale;
	
	//Mapping
	@Column(name="productVendor", nullable=false)
	//Validation
	@NotEmpty @Size(max = 50)
	private String vendor;
	
	//Mapping
	@Column(name="productDescription", nullable=false)
	//Validation
	@NotEmpty
	private String description;

	//Mapping
	@Column(name="quantityInStock", nullable=false)
	//Validation
	@NotNull
	private Integer quantityInStock;
	
	//Mapping
	@Column(name="buyPrice", nullable=false)
	//Validation
	@NotNull
	private BigDecimal buyPrice;

	//Mapping
	@Column(name="MSRP", nullable=false)
	//Validation
	@NotNull
	private BigDecimal msrp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductLine getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getMsrp() {
		return msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}
}
