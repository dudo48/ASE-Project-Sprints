package org.notify.notification_api.infrastructure;
import java.util.ArrayList;
import java.util.List;

import org.notify.notification_api.model.NotificationTemplate;

public class MemoryNotificationTemplateAccessLayer implements INotificationTemplateDataAccessLayer {
	private ArrayList<NotificationTemplate> templates;
	
	public MemoryNotificationTemplateAccessLayer() {
		templates = new ArrayList<>();
	}
	@Override
	public boolean AddTemplate(NotificationTemplate template) {
		templates.add(template);
		return true;
	}

	@Override
	public boolean updateTemplate(int id, NotificationTemplate template) {
		if(id >= templates.size() || id < 0) 
			return false;
		templates.set(id, template);
		return true;
	}

	@Override
	public boolean deleteTemplate(int templateID) {
		templates.remove(templateID);
		return true;
	}

	@Override
	public NotificationTemplate getTemplate(int id) {
		
		return templates.get(id);
	}

	@Override
	public ArrayList<NotificationTemplate> searchTemplate() {
		return templates;
	}
	
	public int nextId() {
		int mx = -1;
		for(int i = 0; i < templates.size(); i++) {
			mx = Math.max(mx, templates.get(i).getId());
		}
		return mx;
	}
	
}
