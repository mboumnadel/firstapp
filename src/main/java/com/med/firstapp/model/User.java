package com.med.firstapp.model;


import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import com.med.firstapp.rest.UserResource;



@InjectLinks({
		@InjectLink(
				   style = Style.ABSOLUTE,
		           resource = UserResource.class,
		           method = "getUsersById",
		           bindings = @Binding(name = "userId", value = "${instance.id}"),
		           rel = "header" 
		   )
})

@XmlRootElement(name = "user") 
public class User implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private int id; 
   private String name;
   private String profession;  
   
   
   @InjectLinks({
	   @InjectLink(
			   style = Style.ABSOLUTE,
	           resource = UserResource.class,
	           method = "getUsersById",
	           bindings = @Binding(name = "userId", value = "${instance.id}"),
	           rel = "self" 
	   ),
	   @InjectLink(
			   style = Style.ABSOLUTE,
	           resource = UserResource.class,
	           method = "getUsersById",
	           bindings = @Binding(name = "userId", value = "${instance.id}"),
	           rel = "self2" 
	   )
   })
   @XmlElement(name = "link")
   @XmlElementWrapper(name = "links")
   @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
   private List<Link> links;
   
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

//   public void setSelf(Link link){
//	   this.self = link;
//   }
//   public Link getSelf(){
//	   return self;
//   }
   
   @Override
	public String toString() {
		return "User[Id=" + id + ", Name=" + name + ", Profession=" + profession + "]";
	}
} 