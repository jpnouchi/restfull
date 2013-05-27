package web;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import business.MessageBO;

public class UserMessagesResource extends ServerResource {
	@Get("xml")
	public Representation getXML() {
		String username = (String) getRequest().getAttributes().get("username");
		String xml = MessageBO.getAllXMLForUser(username);
		Representation representation = new StringRepresentation(xml, MediaType.APPLICATION_XML);
		
		return representation;
	}

	@Get("json")
	public Representation getJSON() {
		String username = (String) getRequest().getAttributes().get("username");
		String json = MessageBO.getAllJSONForUser(username);
		Representation representation = new StringRepresentation(json, MediaType.APPLICATION_JSON);
		
		return representation;
	}
}
