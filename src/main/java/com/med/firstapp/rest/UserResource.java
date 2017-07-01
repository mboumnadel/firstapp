package com.med.firstapp.rest;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.med.firstapp.model.User;

@XmlRootElement(name = "userResourceXX")
public class UserResource {

	private User user;

	@InjectLinks({
		@InjectLink(
				style = Style.ABSOLUTE,
				resource = UserRestController.class,
				method = "getUserById",
				bindings = @Binding(name = "userId", value = "${instance.user.id}"),
				rel = "inject-self-1"
				),
		@InjectLink(
				style = Style.ABSOLUTE,
				resource = UserRestController.class,
				method = "getUserById",
				bindings = @Binding(name = "userId", value = "${instance.user.id}"),
				rel = "inject-self-2"
				)
	})

	@XmlElementWrapper(name = "links")
	@XmlElement(name = "link")
	@XmlJavaTypeAdapter(CustomLinkAdapter.class)
	@JsonProperty("links")
	private List<Link> links;

	public UserResource(){
		System.out.println("UserResource empty constructor");
	}
	public UserResource(User user){
		System.out.println("UserResource constructor : " + user.getId());
		this.user = user;
	}

	@JsonUnwrapped
	@XmlElement
	public User getUser(){
		System.out.println("UserResource getUser");
		return user;
	}
}
