package com.rest.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.logic.FileManagement;

@Path("/fileOwner")
public class FileModificationController 
{
	@POST
	@Path("/createFolder")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response createFolder(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path) 
	{
		return Response.status(200).entity(FileManagement.createFolder(sc.getUserPrincipal().getName(), filename, path)).build();
	}
	
	@POST
	@Path("/deleteFolder")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response deleteFolder(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path) 
	{
		return Response.status(200).entity(FileManagement.deleteFileOrFolder(sc.getUserPrincipal().getName(), path, filename)).build();
	}
	
	@POST
	@Path("/tagFile")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response tagFile(@Context SecurityContext sc, @FormParam("tag") String tag, @FormParam("filename") String filename, @FormParam("path") String path) 
    {
		return Response.status(200).entity(FileManagement.tagFile(tag, filename, path, sc.getUserPrincipal().getName())).build();
    }
    
	@POST
	@Path("/removeTagFromFile")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response removeTagFromFile(@Context SecurityContext sc, @FormParam("tag") String tag, @FormParam("filename") String filename, @FormParam("path") String path) 
    {
		return Response.status(200).entity(FileManagement.removeTagFromFile(tag, filename, path, sc.getUserPrincipal().getName())).build();
    }
    
	@POST
	@Path("/shareFile")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response shareFile(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path, @FormParam("username") String username) 
    {
		return Response.status(200).entity(FileManagement.shareFile(filename, path, sc.getUserPrincipal().getName(), username)).build();
    }
    
	@POST
	@Path("/unshareFile")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response unshareFile(@Context SecurityContext sc,  @FormParam("filename") String filename,  @FormParam("path") String path,  @FormParam("username") String username) 
    {
		return Response.status(200).entity(FileManagement.unshareFile(filename, path, sc.getUserPrincipal().getName(), username)).build();
    }
}
