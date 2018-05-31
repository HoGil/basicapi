package com.gilho.appengine;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;






//dr-chuck-tutorials.appspot.com/

@Api(
		name = "employees",
		version = "v1",
		namespace = 
		@ApiNamespace(
			ownerDomain = "appengine.gilho.com",
			ownerName = "appengine.gilho.com",
			packagePath = ""
		),
		// start exclude
		issuers = {
			@ApiIssuer(
				name = "firebase",
				issuer = "https://securetoken.google.com/dr-chuck-tutorials",
				jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system" + ".gserviceaccount.com"
			)
		}
		// end exclude		
	)


public class Employees {
	
	/**
	* Employees receives an employee id number as a GET
	* Sends the name, email address back
	*/ 
	
	// echos message sent by a POST request
	// payload key needs to match either reference variable here or name of string variable (I think latter)
	@ApiMethod(name = "echo")
	public Message echo(Message message) {
		return doEcho(message);
	}
	
	private Message doEcho(Message message) {
		String tempEcho = message.getMessage();
		message.setMessage("Echoed Message; " + tempEcho);
				
		return message;
	}
	
	// sends a random message back based on receiving a successful GET request
	@ApiMethod(name="echo2", httpMethod=ApiMethod.HttpMethod.GET)
	public Message echo2(Message message) {
		message.setMessage("This is a successful get request");
		return message;
	}
	
	// this is an example of a parametized path where the id value is received
	// perform some business logic and send back two JSON elements
	@ApiMethod(name="echo3",
			httpMethod=ApiMethod.HttpMethod.GET,
			path="echo3/{id}"
			)
	public Message echo3(Message message, @Named("id") String id) {
		if (id == "one") { // it is fucking one!!
			message.setMessage("echo3 is a success one");
			return message;
		} else if (id == "two") {
			message.setMessage("echo3 is a success two");
			return message;
		} else {
			
			message.setMessage("echo3 id: " + id);
			// maybe try and send something back here
			// better yet, display the god damn id!!
			message.setLabel("echo3 Label");
			return message;
		}
		
	}
	
	// send back 4 JSON elements with a new type of java bean
	@ApiMethod(name="getDetails", 
			httpMethod=ApiMethod.HttpMethod.GET,
			path="getDetails/{id}") 
	public Details getDetails(Details details, @Named("id") String id) {
		//change the details path to getDetails
		if (id == "one") {
			details.setId(id);
			details.setName("Name: one");
			details.setEmail("one@gmail.com");
			details.setStatus(true);
			return details;
		} else if (id == "two") {
			details.setId(id);
			details.setName("Name: two");
			details.setEmail("two@gmail.com");
			details.setStatus(false);
			return details;
			
		} else {
			details.setId(id);
			details.setName("Name: three");
			details.setEmail("three@gmail.com");
			details.setStatus(false);
			return details;
		}
		
	}
	
	
	// echos two JSON elements back from a POST request payload
	@ApiMethod(name="echo5",
			httpMethod=ApiMethod.HttpMethod.POST,
			path="echo5/{id}"
			)
	public Message echo5(Message message, @Named("id") String id) {
		if (id == "one") { // it is fucking one!!
			message.setMessage("echo5 is a success one");
			return message;
		} else if (id == "two") {
			message.setMessage("echo5 is a success two");
			return message;
		} else {
			
			message.setMessage("Message: " + message.getMessage());
			// maybe try and send something back here
			// better yet, display the god damn id!!
			message.setLabel("Label : " + message.getLabel());
			return message;
		}
		
	}
	
	
	
	
	
	
}
