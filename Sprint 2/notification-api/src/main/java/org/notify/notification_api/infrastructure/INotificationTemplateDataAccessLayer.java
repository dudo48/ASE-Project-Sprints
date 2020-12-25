package org.notify.notification_api.infrastructure;
import java.util.ArrayList;

import org.notify.notification_api.model.NotificationTemplate;

public interface INotificationTemplateDataAccessLayer {
	public boolean AddTemplate(NotificationTemplate template);
	public boolean updateTemplate(int id, NotificationTemplate template);
	public boolean deleteTemplate(int templateID);
	public NotificationTemplate getTemplate(int id );
	public ArrayList<NotificationTemplate> searchTemplate ();
		

}
