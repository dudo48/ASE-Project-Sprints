package com.notification.rest.model;


public class Notification {

    Integer notificationID;
    boolean status;
    String finalContent;
    String receiver;
    String subject;
    public Notification(){ }
    public Notification(String finalContent, String receiver) {

        this.status = false;
        this.finalContent = finalContent;
        this.receiver = receiver;
    }
    public Notification(Integer notificationID, String finalContent, String receiver ,String subject) {
        this.notificationID = notificationID;
        this.status = false;
        this.finalContent = finalContent;
        this.receiver = receiver;
        this.subject=subject;
    }

    @Override
    public String toString() {
        return "Notification{" +
                //"notificationID=" + notificationID +
                //", status=" + status +
                " subject='" + subject + '\'' +
                ", finalContent='" + finalContent + '\'' +
                ", receiver='" + receiver + '\'' +

                '}';
    }

    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNotificationID() { return notificationID; }

    public void setNotificationID(int notificationID) { this.notificationID = notificationID; }

    public boolean isStatus() { return status; }

    public void setStatus(boolean status) { this.status = status; }


    public String getFinalContent() { return finalContent; }

    public void setFinalContent(String finalContent) { this.finalContent = finalContent; }

    public String getReciver() { return receiver; }

    public void setReciver(String reciver) { this.receiver = reciver; }


}
