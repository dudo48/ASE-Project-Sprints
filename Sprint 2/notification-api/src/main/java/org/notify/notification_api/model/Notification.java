package Sprint_2;

import java.util.ArrayList;

 public class Notification {
 protected NotificationTemplate template;
 protected ArrayList<String>MessageParts=new ArrayList<>();
 protected boolean Status;
 protected int ID;
 protected String sendway; //Email or Phone number
 public Notification(){}
 public void setSendway(String sendway)
 {
	 this.sendway=sendway;
 }
 public String getSendway() {
	return sendway;
}
public Notification(NotificationTemplate template, ArrayList<String> messageParts,String sendway) {
	this.template = template;
	MessageParts = messageParts;
	this.sendway = sendway;
}

public NotificationTemplate getTemplate() {
	return template;
 }
 public void setTemplate(NotificationTemplate template) {
	this.template = template;
 }
 public ArrayList<String> getMessageParts() {
	return MessageParts;
 }
 public void setMessageParts(ArrayList<String> messageParts) {
	MessageParts = messageParts;
 }
 public boolean isStatus() {
	return Status;
 }
 public void setStatus(boolean status) {
	Status = status;
 }
 public int getID() {
	return ID;
 }
 public void setID(int id) {
	 ID=id;
 }
 public String completeContent()
 {
	 for(int i=0;i<MessageParts.size();)
	 {
		 int var=0;
		 while(var<template.Content.length())
		 {
			 if(template.Content.charAt(var)=='{')
			 {
				 if(var+3<template.Content.length())
				 {
				 String word="";
				 for(int j=var;j<var+3;j++)
				 {
					 word+=template.Content.charAt(j);
				 }
				 template.Content=template.Content.replace(word, MessageParts.get(i));
				 
				 i++;
				 var+=2;
				 if(i==MessageParts.size())
		        	 break; 
				 }
				 else
					 break;
			 }
			 var++;
		 }
		 if(var>=template.Content.length() && i<MessageParts.size())
			 break;
	 }
	 return template.Content;
 }
}
