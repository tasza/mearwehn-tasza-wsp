package com.rest.service;

import java.io.File;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import com.logic.FileManagement;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/fileOwner")
public class FileModificationController 
{
	@POST
	@Path("/createFolder")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response createFolder(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path) 
	{
		return Response.status(200).entity(Boolean.toString(FileManagement.createFolder(sc.getUserPrincipal().getName(), filename, path))).build();
	}
	
	@POST
	@Path("/uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail, 
		@FormDataParam("path") String path, 
		@Context SecurityContext sc
		) 
	{
		System.out.println("try upload file"); 
		return Response.status(200).entity(Boolean.toString(FileManagement.uploadFile(uploadedInputStream, fileDetail.getFileName(), path, sc.getUserPrincipal().getName()))).build();
		 
	}
	
	@POST
    @Path("/downloadFile")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response downloadFile(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path, @FormParam("type") String type) 
	{
		File output = FileManagement.downloadFile(sc.getUserPrincipal().getName(), filename, path, type); 
		if(output != null)
			return Response.ok(FileManagement.downloadFile(sc.getUserPrincipal().getName(), filename, path, type)).header("Content-Disposition", "attachment; filename="+filename).build();
		return null; 
    }
	
	@POST
	@Path("/deleteFolder")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response deleteFolder(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path, @FormParam("type") String type) 
	{
		return Response.status(200).entity(Boolean.toString(FileManagement.deleteFileOrFolder(sc.getUserPrincipal().getName(), path, filename, type))).build();
	}
	
	@POST
	@Path("/tagFile")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response tagFile(@Context SecurityContext sc, @FormParam("tag") String tag, @FormParam("filename") String filename, @FormParam("path") String path, @FormParam("type") String type) 
    {
		return Response.status(200).entity(Boolean.toString(FileManagement.tagFile(tag, filename, path, type, sc.getUserPrincipal().getName()))).build();
    }
    
	@POST
	@Path("/removeTagFromFile")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response removeTagFromFile(@Context SecurityContext sc, @FormParam("tag") String tag, @FormParam("filename") String filename, @FormParam("path") String path, @FormParam("type") String type) 
    {
		return Response.status(200).entity(Boolean.toString(FileManagement.removeTagFromFile(tag, filename, path, type, sc.getUserPrincipal().getName()))).build();
    }
    
	@POST
	@Path("/shareFile")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response shareFile(@Context SecurityContext sc, @FormParam("filename") String filename, @FormParam("path") String path, @FormParam("type") String type, @FormParam("username") String username) 
    {
		return Response.status(200).entity(Boolean.toString(FileManagement.shareFile(filename, path, type, sc.getUserPrincipal().getName(), username))).build();
    }
    
	@POST
	@Path("/unshareFile")
	@Produces({ MediaType.TEXT_PLAIN })
    public Response unshareFile(@Context SecurityContext sc,  @FormParam("filename") String filename,  @FormParam("path") String path,  @FormParam("type") String type, @FormParam("username") String username) 
    {
		return Response.status(200).entity(Boolean.toString(FileManagement.unshareFile(filename, path, type, sc.getUserPrincipal().getName(), username))).build();
    }
}
