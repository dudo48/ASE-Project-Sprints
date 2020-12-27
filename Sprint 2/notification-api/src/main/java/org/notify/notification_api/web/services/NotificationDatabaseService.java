package org.notify.notification_api.web.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.notify.notification_api.infrastructure.DatabaseNotificationTemplateAccessLayer;
import org.notify.notification_api.infrastructure.INotificationTemplateDataAccessLayer;
import org.notify.notification_api.infrastructure.MemoryNotificationTemplateAccessLayer;
import org.notify.notification_api.model.LanguageEnum;
import org.notify.notification_api.model.NotificationTemplate;
import org.notify.notification_api.model.Response;

@Path("db")
public class NotificationDatabaseService implements NotificationService{
	INotificationTemplateDataAccessLayer data = new DatabaseNotificationTemplateAccessLayer();
	
	/*@Path("add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)*/
	@Override
	public Response addTemplate(NotificationTemplate t) {
		data.AddTemplate(t);
		return new Response(true, "Added successfully");
	}
	
	/*@Path("{id}/delete")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)*/
	@Override
	public Response deleteTemplate(int id) {
		boolean state = data.deleteTemplate(id);
		if(state) {
			return new Response(state, "Deleted succesfully");
		}
		return new Response(state, "the id is not found");
	}
	
	/*@Path("{id}/read")
	@GET
	@Produces(MediaType.APPLICATION_JSON)*/
	@Override
	public NotificationTemplate getTemplate(@PathParam("id") int id) {
		return data.getTemplate(id);
	}
	
	/*@Path("{id}/update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)*/
	@Override
	public Response updateTemplate(int id, NotificationTemplate t) {
		t.setId(id);
		boolean state = data.updateTemplate(id, t);
		if(state) {
			return new Response(state, "Updated succesfully");
		}
		return new Response(state, "the id is not found");
	}
	
	@Path("read")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NotificationTemplate> readAllTemplates() {
		return data.searchTemplate();
	}
}
