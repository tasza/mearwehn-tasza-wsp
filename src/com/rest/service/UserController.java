package com.rest.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.logic.UserManagement;


@Path("/userController")
public class UserController 
{
	@POST
	@Path("/addUser")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response addUser(@Context SecurityContext sc, @FormParam("username") String username, @FormParam("password") String password) 
	{
		boolean response = UserManagement.addUser(username, password); 
		return Response.status(200).entity(Boolean.toString(response)).build();
	}
	
	@POST
	@Path("/changePassword")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response changePassword(@Context SecurityContext sc, @FormParam("password") String password) 
	{
		boolean response = UserManagement.changeUserPassword(sc.getUserPrincipal().getName(), password); 
		return Response.status(200).entity(Boolean.toString(response)).build();
	}
}
