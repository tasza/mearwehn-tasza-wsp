package com.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.logic.UserManagement;

@Path("/spaceController")
public class SpaceController 
{
	@GET
	@Path("/availableSpace")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getAvailableSpace(@Context SecurityContext sc) 
	{
		long availableSpace = UserManagement.getAvailableSpace(sc.getUserPrincipal().getName()); 
		return Response.status(200).entity(availableSpace).build();
	}
}
