package com.jmunoz.examples.jakarta.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmunoz.examples.jakarta.params.Request01;
import com.jmunoz.examples.jakarta.params.Request02;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Path("/hellomessage")
@Produces(MediaType.TEXT_PLAIN)
public class MessageResource {
	
	@GET
	public Response helloMessage(@QueryParam("message") String msg) {
		String result = "Hello from Jakarta Api (QueryParam), your message is: " + msg;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/{message}")
	public Response helloMessagePath(@PathParam("message") String msg) {
		String result = "Hello from Jakarta Api (PathParam), your message is: " + msg;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/formparam")
	public Response helloMessageFormParam(@FormParam("message") String msg) {
		String result = "Hello from Jakarta Api (POST FormParam), your message is: " + msg;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/headerparam")
	public Response helloMessageHeader(@HeaderParam("message") String msg) {
		String result = "Hello from Jakarta Api (POST HeaderParam), your message is: " + msg;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/bodyparam")
	public Response helloMessageBody(String bodymsg) {
		String result = "Hello from Jakarta Api (POST BodyParam), your message is: " + bodymsg;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/jsonbodyparam")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response helloMessageJsonBody(String jsonBody) {
		Request01 req01 = new Gson().fromJson(jsonBody, Request01.class);
		String result = "Hello from Jakarta Api (POST Json BodyParam), your message is: " + req01;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/jsonarraybodyparam")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response helloMessageJsonArrayBody(String jsonArrayBody) {
		//Parsing as List
		Type listType = new TypeToken<ArrayList<Request02>>(){}.getType();
		List<Request02> listReq02 = new Gson().fromJson(jsonArrayBody, listType);
		//Parsing as array
		Request02[] arrayReq02 = new Gson().fromJson(jsonArrayBody, Request02[].class);

		String result = "Hello from Jakarta Api (POST JsonArray BodyParam), your message is: \nAsList-> " + listReq02 + "   \nAsArray->";
		for(Request02 r2:arrayReq02)
			result += "\n" + r2.toString();
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/cookieparam")
	public Response helloMessageCookie(@CookieParam("message") String cookiemsg) {
		String result = "Hello from Jakarta Api (POST CookieParam), your message is: " + cookiemsg;
		return Response.status(200).entity(result).build();
	}

	public static final String AUTHENTICATION_HEADER = "Authorization";

	@POST
	@Path("/basicauthentication")
	public Response helloMessageBasicAuthentication(@Context HttpServletRequest request) {
		String basicAuth = request.getHeader(AUTHENTICATION_HEADER);
		byte[] decodedBytes = Base64.getDecoder().decode(basicAuth.replace("Basic ", ""));
		String decodedString = new String(decodedBytes);
		String user = decodedString.split(":")[0];
		String password = decodedString.split(":")[1];
		String resultAuth = "Authentication Failed !!!";
		if(user.equals("user") && password.equals("password")){
			resultAuth = "Authenticated Successfully !!!";
		}
		String result = "Hello from Jakarta Api (POST Basic Authentication), your message is: " + resultAuth;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/multipartformdata")
	@Consumes("multipart/form-data")
	public Response helloMessageMultipartFormData(@Context HttpServletRequest request) {
		try{
			Part file = request.getPart("file"); //file es el campo donde se enviará el archivo en el request.
			InputStream fileContent = file.getInputStream();
			//reading the text content of the file.txt
			String txt = new String(fileContent.readAllBytes());
			String result = "Hello from Jakarta Api (POST MultiPartFormData), your message is: " + txt;
			return Response.status(200).entity(result).build();
		}
		catch (IOException | ServletException ex){
			//Do some exception management
		}
		String result = "Hello from Jakarta Api (POST MultiPartFormData), Multipart File Failed to read.";
		return Response.status(200).entity(result).build();
	}

}