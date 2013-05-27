package web;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import business.MessageBO;

public class SearchMessagesResource extends ServerResource {
	@Get("xml")
	public Representation getXML() {
		String search_item = (String) getRequest().getAttributes().get("search_item");
		String xml = MessageBO.searchAllXML(search_item);
		Representation representation = new StringRepresentation(xml, MediaType.APPLICATION_XML);

		return representation;
	}
	
	@Get("json")
	public Representation getJSON() {
		String search_item = (String) getRequest().getAttributes().get("search_item");
		String json = MessageBO.searchAllJSON(search_item);		
		Representation representation = new StringRepresentation(json, MediaType.APPLICATION_JSON);

		return representation;
	}
}
