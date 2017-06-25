package com.med.firstapp.model;


import java.io.Serializable;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter; 

@XmlRootElement(name = "user") 
public class User implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private int id; 
   private String name; 
   private String profession;  

   @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
   @XmlElement(name="link")
   private Link self;
   
   public User(){} 
   
   public User(int id, String name, String profession){  
      this.id = id; 
      this.name = name; 
      this.profession = profession; 
   }  
   public int getId() { 
      return id; 
   }  
   @XmlElement 
   public void setId(int id) { 
      this.id = id; 
   } 
   public String getName() { 
      return name; 
   } 
   @XmlElement
   public void setName(String name) { 
      this.name = name; 
   } 
   public String getProfession() { 
      return profession; 
   } 
   @XmlElement 
   public void setProfession(String profession) { 
      this.profession = profession; 
   }

   public void setSelf(Link link){
	   this.self = link;
   }
   public Link getSelf(){
	   return self;
   }
   
   @Override
	public String toString() {
		return "User[Id=" + id + ", Name=" + name + ", Profession=" + profession + "]";
	}
} 