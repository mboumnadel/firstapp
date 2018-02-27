package com.med.firstapp.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "email")
public class EmailDto implements Serializable {


	private static final long serialVersionUID = 1L;

	private String address;
	private String type;

	public EmailDto(){
		System.out.println("EmailDto default constructor");
	}

	public EmailDto(String address, String type){
		this.address = address;
		this.type = type;
	}

	public String getAddress() {
		System.out.println("EmailDto getAddress");
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EmailDto [address=" + address + ", type=" + type + "]";
	}




}
