package com.med.firstapp.rest;



import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.med.firstapp.model.User;


@Path("/users")
	public class UserResource {

	@Context UriInfo uriInfo;

	private UserService userService = new UserService();

	public UserResource(){
		System.out.println("UserService constructor");
	}



	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<User> getUsers(){

		return userService.getUsers();
	}

	@GET
	@Path("{userId}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getUserById(@PathParam("userId") Integer userId) {

		User user = userService.getUserById(userId);

		if(user == null){
			throw new InternalServerErrorException();
		}

		UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
		uriBuilder.path(UserResource.class);
		uriBuilder.path(UserResource.class, "getUserById");

		Link self = Link.fromUriBuilder(uriBuilder).rel("self").build("3");

		return  Response.ok(user).links(self).build();
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(User user){

		user = userService.addUser(user);

		return user;
	}

}
