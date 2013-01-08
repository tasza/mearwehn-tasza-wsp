package com.rest.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.rest.client.FolderServiceClient;
import com.rest.model.Folder;
import com.sun.jersey.api.client.ClientResponse;

@Path("/folder")
public class FolderController {
	@POST
	@Path("/add")
	@Produces({ MediaType.TEXT_PLAIN })
	public ClientResponse createFolder(@Context SecurityContext sc, @FormParam("filename") String filename) 
	{
		System.out.println("ADDING FOLDER!!");
		FolderServiceClient client = new FolderServiceClient();
		Folder folder = new Folder(filename,sc.getUserPrincipal().getName());
		return client.addFolder(folder);
	}
	
	@GET
	@Path("/test")
	public String sayBye(@Context SecurityContext sc) {
		return "Logged in as: "+sc.getUserPrincipal().getName()+"!";
	}
}
