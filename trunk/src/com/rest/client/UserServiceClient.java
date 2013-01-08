package com.rest.client;

import javax.ws.rs.core.MediaType;

import com.rest.model.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserServiceClient {
	private Client client;
	private WebResource webResource;
	
	public UserServiceClient(){
		client = Client.create();
		webResource = client.resource("http://localhost:8080/Lab5/rest/user");
	}
	
	public ClientResponse addUser(User user){
		return webResource.path("/add").type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).post(ClientResponse.class, user);
	}
}
