package web;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import business.UserBO;
import exception.InvalidXMLException;
import exception.ItemAlreadyExistsException;

public class UsersResource extends ServerResource {
	@Get("xml")
	public Representation getXML() {
		String xml = UserBO.getAllXML();
		Representation representation = new StringRepresentation(xml, MediaType.APPLICATION_XML);

		return representation;
	}

	@Get("json")
	public Representation getJSON() {
		String json = UserBO.getAllJSON();
		Representation representation = new StringRepresentation(json, MediaType.APPLICATION_JSON);

		return representation;
	}

	@Post
	public Representation createtUser(Representation entity) {
		Representation representation = null;		
		
		try {
			representation = new StringRepresentation(UserBO.create(entity.getText()), MediaType.APPLICATION_XML);
		} catch (InvalidXMLException e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			representation = new StringRepresentation("Invalid XML.", MediaType.TEXT_PLAIN);
		} catch (ItemAlreadyExistsException e) {
			setStatus(Status.CLIENT_ERROR_FORBIDDEN);
			representation = new StringRepresentation("Item already exists.", MediaType.TEXT_PLAIN);
		} catch (IOException e) {
			setStatus(Status.SERVER_ERROR_INTERNAL );			
		}

		return representation;
	}
}
