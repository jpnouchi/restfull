package web;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import business.MessageBO;
import exception.ItemNotFoundException;

public class MessageResource extends ServerResource {
	@Get("xml")
	public Representation getXML() {
		String messageID = (String) getRequest().getAttributes().get("messageID");
		StringRepresentation representation = null;	
		
		String xml = MessageBO.getXML(messageID);
		representation = new StringRepresentation(xml, MediaType.APPLICATION_XML);
		if (xml != null) {
			return representation;
		} else {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
	}

	@Get("json")
	public Representation getJSON() {
		String messageID = (String) getRequest().getAttributes().get("messageID");
		StringRepresentation representation = null;	
		
		String json = MessageBO.getJSON(messageID);
		representation = new StringRepresentation(json, MediaType.APPLICATION_JSON);
		if (json != null) {
			return representation;
		} else {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
	}

	@Delete
	public void deleteMessage() {
		String messageID = (String) getRequest().getAttributes().get("messageID");
		try {
			MessageBO.delete(messageID);
			setStatus(Status.SUCCESS_NO_CONTENT);
		} catch (ItemNotFoundException e) {
			setStatus(Status.CLIENT_ERROR_NOT_FOUND);
		}
	}
}
