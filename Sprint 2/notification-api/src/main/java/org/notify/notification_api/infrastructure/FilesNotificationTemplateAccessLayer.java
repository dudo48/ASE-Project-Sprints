package org.notify.notification_api.infrastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.notify.notification_api.model.LanguageEnum;
import org.notify.notification_api.model.NotificationTemplate;

public class FilesNotificationTemplateAccessLayer 
			implements INotificationTemplateDataAccessLayer{
	
	private ArrayList<NotificationTemplate> templates;
	private final String FILE_NAME = "myData.txt";
	
	public FilesNotificationTemplateAccessLayer() {
		File file = new File(FILE_NAME);
		if(!file.exists()) {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		templates = readFromFile();
		templates.add(new NotificationTemplate(1, "gg", "ggg", LanguageEnum.English));
	}
	
	private ArrayList<NotificationTemplate> readFromFile() {
		ArrayList<NotificationTemplate> templates = new ArrayList<>();
		try {
			Scanner scan = new Scanner(new File(FILE_NAME));
			while(scan.hasNext()) {
				int id = scan.nextInt();
				String content = scan.next();
				String subject = scan.next();
				LanguageEnum language = LanguageEnum.valueOf(scan.next());
				templates.add(new NotificationTemplate(id, content, subject, language));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return templates;
	}
	
	private void writeToFile() {
		try {
			FileWriter writer = new FileWriter(new File(FILE_NAME));
			for(int i = 0; i < templates.size(); i++) {
				writer.write(templates.get(i).getId() + " " + templates.get(i).getContent() + " "
						+ templates.get(i).getSubject() + " " + templates.get(i).getLanguage());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean AddTemplate(NotificationTemplate template) {
		templates = this.readFromFile();
		templates.add(template);
		writeToFile();
		return true;
	}

	@Override
	public boolean updateTemplate(int id, NotificationTemplate template) {
		templates = this.readFromFile();
		for(int i = 0; i < templates.size(); i++) {
			if(templates.get(i).getId() == id) {
				templates.set(i, template);
				writeToFile();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteTemplate(int templateID) {
		templates = this.readFromFile();
		for(int i = 0; i < templates.size(); i++) {
			if(templates.get(i).getId() == templateID) {
				templates.remove(i);
				writeToFile();
				return true;
			}
		}
		return false;
	}

	@Override
	public NotificationTemplate getTemplate(int id) {
		templates = this.readFromFile();
		NotificationTemplate temp = null;
		for(int i = 0; i < templates.size(); i++) {
			if(id == templates.get(i).getId()) {
				temp = templates.get(i);
			}
		}
		writeToFile();
		return temp;
	}

	public int nextId() {
		int mx = -1;
		for(int i = 0; i < templates.size(); i++) {
			mx = Math.max(mx, templates.get(i).getId());
		}
		return mx;
	}
	@Override
	public ArrayList<NotificationTemplate> searchTemplate() {
		return this.readFromFile();
	}
	
	public int size() {
		return templates.size();
	}

}
