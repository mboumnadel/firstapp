package com.med.firstapp.rest;



import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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

import com.google.common.collect.Lists;
import com.med.firstapp.model.User;


@Path("/users")
	public class UserRestController {

	@Context UriInfo uriInfo;

	private UserService userService = new UserService();

	public UserRestController(){
		System.out.println("UserRestController constructor");
	}



	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<UserResource> getUsers(){

		Function<User, UserResource> toResource = new Function<User, UserResource>() {
			@Override public UserResource apply(User user) { return new UserResource(user); }
		};

		List<User> users = userService.getUsers();
		//List<UserResource> userResources = Lists.transform(users, toResource::apply);
		List<UserResource> userResources = Lists.transform(users, (User user)->{return new UserResource(user);});

		//Make the the transformation happen once and forever
		userResources = Arrays.asList(userResources.toArray(new UserResource[0]));

		return userResources;
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
		uriBuilder.path(UserRestController.class);
		uriBuilder.path(UserRestController.class, "getUserById");

		Link self = Link.fromUriBuilder(uriBuilder).rel("prog-self").build("3");

		UserResource userResource = new UserResource(user);

		return  Response.ok(userResource).links(self).build();
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserResource addUser(User user){

		user = userService.addUser(user);

		return new UserResource(user);
	}

}
