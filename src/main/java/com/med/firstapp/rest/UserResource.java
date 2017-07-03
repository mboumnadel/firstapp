package com.med.firstapp.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.med.firstapp.model.User;

@XmlRootElement(name = "user")
public class UserResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	@InjectLinks({
		@InjectLink(
				style = Style.ABSOLUTE,
				resource = UserRestController.class,
				method = "getUserById",
				bindings = @Binding(name = "userId", value = "${instance.id}"),
				rel = "inject-self-1"
				),
		@InjectLink(
				style = Style.ABSOLUTE,
				resource = UserRestController.class,
				method = "getUserById",
				bindings = @Binding(name = "userId", value = "${instance.id}"),
				rel = "inject-self-2"
				)
	})

	@XmlElementWrapper(name = "links")
	@XmlElement(name = "link")
	@XmlJavaTypeAdapter(CustomLinkAdapter.class)
	@JsonProperty("links")
	private List<Link> links;

	public UserResource(){
		System.out.println("UserResource default constructor");
		this.user = new User();
	}

	public UserResource(User user){
		System.out.println("UserResource constructor user.getId() : " + user.getId());
		this.user = user;
	}


	@XmlTransient
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Link> getLinks() {
		return links;
	}



	@XmlTransient
	public void setUser(User user) {
		System.out.println("setUser");
		this.user = user;
	}

	public User getUser() {
		System.out.println("getUser");
		return user;
	}



	public void setId(int id) { System.out.println("SET ID"); user.setId(id); }

	public int getId(){ System.out.println("GET ID"); return user.getId(); };


	public void setName(String name) { System.out.println("SET NAME"); user.setName(name); }

	public String getName(){ System.out.println("GET Name"); return user.getName(); };


	public void setProfession(String profession){ System.out.println("SET Profession"); user.setProfession(profession); };

	public String getProfession(){ System.out.println("GET Profession"); return user.getProfession(); };


	@XmlRootElement(name = "users")
	public static class UserResources {


		private List<UserResource> userResources = new ArrayList<>();


		public UserResources(){}

		public UserResources(List<UserResource> userResources){
			this.userResources = userResources;
		}

		@XmlElement(name = "user")
		@JsonProperty("users")
		public void setUserResources(List<UserResource> userResources){
			this.userResources = userResources;
		}
		public List<UserResource> getUserResources(){
			return this.userResources;
		}
	}
}
