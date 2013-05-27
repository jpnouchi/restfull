package web;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import business.UserBO;
import exception.InvalidXMLException;
import exception.ItemNotFoundException;

public class UserResource extends ServerResource {
	@Get("xml")
	public Representation getXML() {
		String username = (String) getRequest().getAttributes().get("username");
		StringRepresentation representation = null;	
		
		String xml = UserBO.getXML(username);
		representation = new StringRepresentation(xml, MediaType.APPLICATION_XML);
		if (xml != null) {
			return representation;
		} else {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
	}

	// TODO: this doesn't work, because the Accept header doesn't work in restlet.
	@Get("json")
	public Representation getJSON() {
		String username = (String) getRequest().getAttributes().get("username");
		StringRepresentation representation = null;	
		
		String json = UserBO.getJSON(username);
		representation = new StringRepresentation(json, MediaType.APPLICATION_JSON);
		if (json != null) {
			return representation;
		} else {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
	}

	@Put
	public Representation updateUser(Representation entity) {
		Representation representation = null;
		
		try {
			representation = new StringRepresentation(UserBO.update(entity.getText()), MediaType.APPLICATION_XML);
		} catch (InvalidXMLException e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			representation = new StringRepresentation("Invalid XML.", MediaType.TEXT_PLAIN);
		} catch (ItemNotFoundException e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			representation = new StringRepresentation("Item not found.", MediaType.TEXT_PLAIN);
		} catch (IOException e) {
			setStatus(Status.SERVER_ERROR_INTERNAL );
		}

		return representation;
	}
	
	@Delete
	public void deleteUser() {
		try {
			String username = (String) getRequest().getAttributes().get("username");			
			UserBO.delete(username);
			setStatus(Status.SUCCESS_NO_CONTENT);
		} catch (ItemNotFoundException e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
		}
	}
}
