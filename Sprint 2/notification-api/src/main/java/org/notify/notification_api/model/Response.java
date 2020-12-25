package org.notify.notification_api.model;

public class Response {
	private boolean state;
	private String message;
	
	public Response(boolean state, String message) {
		this.state = state;
		this.message = message;
	}
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
