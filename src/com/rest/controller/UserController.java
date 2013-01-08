package com.rest.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.rest.client.UserServiceClient;
import com.rest.model.User;
import com.sun.jersey.api.client.ClientResponse;

@Path("/user")
public class UserController {
	@POST
	@Path("/add")
	@Produces({ MediaType.TEXT_PLAIN })
	public ClientResponse addUser(@Context SecurityContext sc, @FormParam("username") String username, @FormParam("password") String password) 
	{
		UserServiceClient client = new UserServiceClient();
		User user = new User(username,password);
		return client.addUser(user);
		//return Response.status(200).entity(Boolean.toString(response)).build();
	}
}
