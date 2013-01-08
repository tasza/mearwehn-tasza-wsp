package com.rest.client;

import javax.ws.rs.core.MediaType;

import com.rest.model.Folder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FolderServiceClient {
	private Client client;
	private WebResource webResource;
	
	public FolderServiceClient(){
		client = Client.create();
		webResource = client.resource("http://localhost:8080/Lab5/rest/folder");
	}
	
	public ClientResponse addFolder(Folder folder){
		return webResource.path("/add").type(MediaType.TEXT_PLAIN).accept(MediaType.APPLICATION_XML).post(ClientResponse.class, folder);
	}
}
