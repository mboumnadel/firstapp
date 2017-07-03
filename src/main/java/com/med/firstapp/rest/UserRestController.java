package com.med.firstapp.rest;



import java.util.Arrays;
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

import com.google.common.collect.Lists;
import com.med.firstapp.model.Email;
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
	public Response getUsers(){

		List<User> users = userService.getUsers();
		List<UserResource> userResources = Lists.transform(users, (User user)->{return new UserResource(user);});

		//Make the the transformation happen once and forever
		userResources = Arrays.asList(userResources.toArray(new UserResource[0]));

        //return Response.ok(new GenericEntity<List<UserResource>>(userResources) {}).build();

        return Response.ok(new UserResource.UserResources(userResources)).build();
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
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addUser(UserResource userResource ){

		System.out.println("UserRestController addUser");

		User user = userResource.getUser();

		user = userService.addUser(user);

		UserResource userResource1 = new UserResource(user);

		return  Response.ok().entity(userResource1).build();

	}

	@GET
	@Path("/email")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Email getEmail(){

		System.out.println("getEmailXml");

		Email email = new Email("GET EMAIL AD", " TYTPPE00E");

		return email;
//		return  Response.ok().entity(email).build();
	}

	@POST
	@Path("/email")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response addEmail(Email email){


		System.out.println("addEmailXml");

		return  Response.ok().entity(email).build();
	}



}
