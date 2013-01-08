package com.rest.app;

import javax.ws.rs.ApplicationPath;

import com.sun.jersey.api.core.PackagesResourceConfig;

@ApplicationPath("/app")
public class RestAppClient extends PackagesResourceConfig {

	public RestAppClient() {
		super("com.rest.client.controller");
		// TODO Auto-generated constructor stub
	}

	
}
