package org.notify.notification_api.web.services;

import org.notify.notification_api.model.NotificationTemplate;
import org.notify.notification_api.model.Response;

public interface NotificationService {
	public Response addTemplate(NotificationTemplate t); // create
	
	public Response deleteTemplate(int id); // delete
	
	public NotificationTemplate getTemplate(int id); // read
	
	public Response updateTemplate(int id, NotificationTemplate t); // update
}
