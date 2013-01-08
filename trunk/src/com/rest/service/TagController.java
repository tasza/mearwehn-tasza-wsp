package com.rest.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.db.logic.TagManager;
import com.db.logic.TagedFilesManager;
import com.logic.FileManagement;
import com.logic.TagManagement;

@Path("/tagController")
public class TagController 
{
	@POST
	@Path("/deleteTag")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response deleteTag(@FormParam("tag") String tag) 
    {
		return Response.status(200).entity(TagManagement.deleteTag(tag)).build();
    }
	
	@POST
	@Path("/addTag")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response addTag(String tag) 
    {
		return Response.status(200).entity(TagManagement.addTag(tag)).build();
    }
}

