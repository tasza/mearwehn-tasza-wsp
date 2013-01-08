package com.rest.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.logic.FileSearch;



@Path("/fileSearchController")
public class FileSearchController 
{
	@GET
	@Path("/listAllUserFiles")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response listAllUserFiles(@Context SecurityContext sc)
    {
        return Response.status(200).entity(FileSearch.listAllUserFiles(sc.getUserPrincipal().getName())).build(); 
    }
    
	@GET
	@Path("/listAllUserTagedFiles")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response listAllUserTagedFiles(@Context SecurityContext sc)
    {
		return Response.status(200).entity(FileSearch.listAllUserTagedFiles(sc.getUserPrincipal().getName())).build(); 
    }
    
	@GET
	@Path("/listAllUserSharedFiles")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response listAllUserSharedFiles(@Context SecurityContext sc)
    {
		return Response.status(200).entity(FileSearch.listAllUserSharedFiles(sc.getUserPrincipal().getName())).build();  
    }
    
	@POST
	@Path("/searchFilesByName")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response searchFilesByName(@Context SecurityContext sc, @FormParam("filename") String filename)
    {
		return Response.status(200).entity(FileSearch.searchFilesByName(sc.getUserPrincipal().getName(), filename)).build();   
    } 
    
	@POST
	@Path("/searchFilesByTag")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response searchFilesByTag(@Context SecurityContext sc, @FormParam("tag") String tag)
    {
		return Response.status(200).entity(FileSearch.searchFilesByTag(sc.getUserPrincipal().getName(), tag)).build();   
    } 
    
	@POST
	@Path("/searchFilesByType")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response searchFilesByType(@Context SecurityContext sc, @FormParam("type") String type)
    {
		return Response.status(200).entity(FileSearch.searchFilesByType(sc.getUserPrincipal().getName(), type)).build();   
    } 
}
