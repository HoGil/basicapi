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

	// note primitive return types are not allowed!
	// just a method that will say hi Gil if used a get
	/*@ApiMethod(name = "sayHi", httpMethod = ApiMethod.HttpMethod.GET)
	public String sayHi() {
		String response = "Hello Gil";
		return response;
	}*/ 
	
	
	// echo message use POST
	@ApiMethod(name = "echo")
	public Message echo(Message message) {
		return doEcho(message);
	}
	// it seems like when a post message with the message keypair is sent, it 
	// automatically creates a Message instance with the value already set
	// in the bean. thats fucking amazing.
	
	private Message doEcho(Message message) {
		String tempEcho = message.getMessage();
		message.setMessage("Echoed Message; " + tempEcho);
				
		return message;
	}
	
	// so this works!!
	// testing out the same method as above but as a GET method
	@ApiMethod(name="echo2", httpMethod=ApiMethod.HttpMethod.GET)
	public Message echo2(Message message) {
		message.setMessage("This is a successful get request");
		return message;
	}
	
	//this doesnt work but think that this is the right path way
	// testing out the same method except with ids
	// for this one, I am testing whether the path needs to repeat the name of the method
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
			return message;
		}
		
	}
	//doesnt work not found
	//testing out echo3 without putting the name on the path
	//think it is totally wrong
	@ApiMethod(name="echo4",
			httpMethod=ApiMethod.HttpMethod.GET,
			path="/{id}"
			)
	public Message echo4(Message message, @Named("id") String id) {
		if (id == "one") {
			message.setMessage("echo4 is a success one");
			return message;
		} else if (id == "two") {
			message.setMessage("echo4 is a success two");
			return message;
		} else {
			return message;
		}
		
	}
	
	
	
	@ApiMethod(name="getDetails", 
			httpMethod=ApiMethod.HttpMethod.GET,
			path="getDetails/{id}") 
	private Details getDetails(Details details, @Named("id") String id) {
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
			return details;
		}
		
		
		 
	}
	
	
	
	
}
