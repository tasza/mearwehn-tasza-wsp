package com.rest.service;

import java.io.File;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.config.Config;
import com.rest.forms.SpaceManager;
import com.rest.model.Folder;

@Path("/folder")
public class FolderService {
	@POST
	@Path("/add")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response createFolder(Folder folder) {
		System.out.println("createFolder");
		String directory;
		SpaceManager spaceManager = new SpaceManager();
		if (!(spaceManager.getAvailableSpace(folder.getOwner()) > 0)) {
			return Response.status(200).entity("failed").build();
		}
		try {
			directory = Config.path + folder.getOwner() + "/"
					+ folder.getName();
			new File(directory).mkdir();
			System.out.println(directory);
		} catch (Exception e) {
			directory = e.getMessage();
			System.out.println(directory);
		}
		return Response.status(200).entity(directory).build();
	}
}
