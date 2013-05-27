package web;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RESTfulJavaWebService extends Application {	
	public RESTfulJavaWebService(Context parentContext) {
		super(parentContext);		
	}
	
	@Override 
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		// Define routers for users
		router.attach("/users", UsersResource.class);
		router.attach("/users/{username}", UserResource.class);	
		
		// Define routers for messages
		router.attach("/messages", MessagesResource.class);
		router.attach("/messages/{messageID}", MessageResource.class);
		router.attach("/messages/users/{username}", UserMessagesResource.class);
		
		// Searching URI
		router.attach("/messages/search/{search_item}", SearchMessagesResource.class);

		return router;
	}
}
