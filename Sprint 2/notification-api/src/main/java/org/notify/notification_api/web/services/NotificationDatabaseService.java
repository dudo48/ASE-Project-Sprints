package org.notify.notification_api.web.services;

import javax.ws.rs.GET;
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

public class NotificationDatabaseService implements NotificationService{
	INotificationTemplateDataAccessLayer data = new MemoryNotificationTemplateAccessLayer();
	
	
	@Override
	public Response addTemplate(NotificationTemplate t) {
		data.AddTemplate(t);
		return new Response(true, "Added successfully");
	}

	@Override
	public Response deleteTemplate(int id) {
		
		return null;
	}
	
	@Override
	public NotificationTemplate getTemplate(@PathParam("id") int id) {
		
		return data.getTemplate(id);
	}

	@Override
	public Response updateTemplate(int id, NotificationTemplate t) {

		return null;
	}
	
}
