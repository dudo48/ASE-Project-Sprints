package org.notify.notification_api.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationTemplate {
	private int id;
	private String content;
	private String subject;
	private LanguageEnum language;
	public NotificationTemplate() {
		
	}
	public int getId() {
		return id;
	}
	
	/**
	 * @param id
	 * @param content
	 * @param subject
	 * @param language
	 */
	public NotificationTemplate(int id, String content, String subject, LanguageEnum language) {
		
		this.id = id;
		this.content = content;
		this.subject = subject;
		this.language = language;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public LanguageEnum getLanguage() {
		
		return language;
	}
	
	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}
	
	@Override
	public String toString() {
		return "NotificationTemplate [id=" + id + ", content=" + content + ", subject=" + subject + ", language="
				+ language + "]";
	}


}
