package com.notification.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class NotificationReqeust {
    ArrayList<String> placeHolders ;
    Integer templateID;
    String sendingWay;
    static int notificationCounter=1000;
private IGateWay gateway;
    public NotificationReqeust(@JsonProperty("placeHolders") ArrayList<String> placeHolders,
                               @JsonProperty("tempID") Integer templateID,
                               @JsonProperty("receiver") String sendingWay) {
        this.placeHolders = placeHolders;
        this.templateID = templateID;
        this.sendingWay = sendingWay;

    }
   public Notification makeNotification(Templete temp){
        String finalContent=makeNotificationContent(this.placeHolders,temp);
        Notification n = new Notification(notificationCounter++,finalContent,sendingWay,temp.getSubject());
        if(  Character.isDigit(n.getReciver().charAt(0)) )// to check if it is a mail or sms
            gateway= new SmsGateWay();

        else
            gateway=new MailGateWay();
        gateway.send(n);
        return ( n);

    }
   private String makeNotificationContent(ArrayList<String> placeHolders,Templete template){

// this should be for loop for the size of the arraylist but replacefirst does not work because of regix and literal
       /*for(int i=0;i<placeHolders.size();i++)
           content=content.replaceFirst("{x}",placeHolders.get(0));
   */   String content= template.getContent();
        content=content.replace("{x}",placeHolders.get(0));
   if(template.getId()==2018||template.getId()==2019)
        content=content.replace("{y}",placeHolders.get(1));

   return content ;
    }


    public Integer getTemplateID() {
        return templateID;
    }

    public void setTemplateID(Integer templateID) {
        this.templateID = templateID;
    }

    public String getSendingWay() {
        return sendingWay;
    }

    public void setSendingWay(String sendingWay) {
        this.sendingWay = sendingWay;
    }

    public ArrayList<String> getPlaceHolders() {
        return placeHolders;
    }

    public void setPlaceHolders(ArrayList<String> placeHolders) {
        this.placeHolders = placeHolders;
    }
}
