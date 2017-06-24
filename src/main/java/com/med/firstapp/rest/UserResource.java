package com.med.firstapp.rest;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.med.firstapp.model.User;


@Path("/users") 
public class UserResource {

	private List<User> users = new ArrayList<>();
	
	public UserResource(){
		System.out.println("UserService constructor");
		init();
	}
	
	private void init(){

	   User user = new User();
	   user.setId(1);;
	   user.setName("User 1");
	   user.setProfession("profession 1");
	   users.add(user);
	   
	   user = new User();
	   user.setId(2);;
	   user.setName("User 2");
	   user.setProfession("profession 2");
	   users.add(user);
	   
	   user = new User();
	   user.setId(3);;
	   user.setName("User 3");
	   user.setProfession("profession 3");
	   users.add(user);
	}

	@GET
	@Produces({MediaType.TEXT_HTML})
	public String getHtml()
	{
		System.out.println("UserService viewHome");
	   return "<html>. ADADADASD..</html>";
	}

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getText()
	{
		System.out.println("UserService getStartingPage");
		String output = "<h1>Hello World!<h1>" +
				"<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() + "</p<br>";
		return Response.status(200).entity(output).build();
	}
   
   @GET 
   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
   public List<User> getUsers(){

      return users; 
   }
   
   @GET 
   @Path("{userId}") 
   @Produces(MediaType.APPLICATION_JSON) 
   public User getUsersById(@PathParam("userId") Integer userId){

      return users.get(userId); 
   }
   

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public User addUsersJson(User user) {

	   user.setProfession("pro POST");
	   users.add(user);


       return user;
   }
   
}
