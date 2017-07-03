package com.med.firstapp.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	private String address;
	private String type;

	public Email(){
		System.out.println("Email default constructor");
	}

	public Email(String address, String type){
		this.address = address;
		this.type = type;
	}

	public String getAddress() {
		System.out.println("Email getAddress");
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
		return "Email [address=" + address + ", type=" + type + "]";
	}

}
