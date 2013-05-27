package web;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import business.MessageBO;
import exception.InvalidXMLException;
import exception.UserNotFoundException;

public class MessagesResource extends ServerResource {	
	
	
	@Get("json")
	public Representation getJSON() {
		String json = MessageBO.getAllJSON();
		Representation representation = new StringRepresentation(json, MediaType.APPLICATION_JSON);
		
		return representation;
	}
	
	@Get("xml")
	public Representation getXML() {
		String xml = MessageBO.getAllXML();
		Representation representation = new StringRepresentation(xml, MediaType.APPLICATION_XML);
		
		return representation;
	}
	

	
	@Post
	public Representation createtMessage(Representation entity) {
		Representation representation = null;
	
		try {
			representation = new StringRepresentation(MessageBO.create(entity.getText()), MediaType.APPLICATION_XML);
		} catch (InvalidXMLException e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			representation = new StringRepresentation("Invalid XML.", MediaType.TEXT_PLAIN);	
		} catch (UserNotFoundException e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);			
		} catch (IOException e) {
			setStatus(Status.SERVER_ERROR_INTERNAL);			
		}

		return representation;
	}	
}
