package org.notify.notification_api.web.services;

import javax.ws.rs.Path;

import org.notify.notification_api.infrastructure.INotificationTemplateDataAccessLayer;
import org.notify.notification_api.infrastructure.MemoryNotificationTemplateAccessLayer;
import org.notify.notification_api.model.NotificationTemplate;
import org.notify.notification_api.model.Response;

public class NotificationMemoryService implements NotificationService{
	INotificationTemplateDataAccessLayer data = new MemoryNotificationTemplateAccessLayer();
	
	@Override
	public Response addTemplate(NotificationTemplate t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteTemplate(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationTemplate getTemplate(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateTemplate(int id, NotificationTemplate t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
