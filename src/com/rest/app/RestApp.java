package com.rest.app;

import javax.ws.rs.ApplicationPath;

import com.sun.jersey.api.core.PackagesResourceConfig;

@ApplicationPath("/rest")
public class RestApp extends PackagesResourceConfig {

	public RestApp() {
		super("com.rest.service");
		// TODO Auto-generated constructor stub
	}

	
}
