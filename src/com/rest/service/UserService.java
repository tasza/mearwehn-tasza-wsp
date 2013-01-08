package com.rest.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.logic.UserManagement;
import com.rest.model.User;


@Path("/user")
public class UserService 
{
	@POST
	@Path("/add")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response addStudent(User user){
		boolean response = UserManagement.addUser(user.getName(), user.getPassword()); 
		return Response.status(200).entity(Boolean.toString(response)).build();
	}
}
