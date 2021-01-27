package com.notification.rest.model;

import javax.persistence.*;


@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "notificationID")
    Integer notificationID;
    @Column(name = "status")
    boolean status;
    @Column(name = "finalContent")
    String finalContent;
    @Column(name = "Receiver")
    String receiver;
    @Column (name="subject")
    String subject;
    public Notification(){ }
    public Notification(String finalContent, String receiver) {

        this.status = false;
        this.finalContent = finalContent;
        this.receiver = receiver;
    }
    public Notification(Integer notificationID, String finalContent, String receiver ,String subject,boolean status) {
        this.notificationID = notificationID;
        this.status = status;
        this.finalContent = finalContent;
        this.receiver = receiver;
        this.subject=subject;
    }
    public Notification(Integer notificationID, String finalContent, String receiver ,String subject) {
        this.notificationID = notificationID;
        this.finalContent = finalContent;
        this.receiver = receiver;
        this.subject=subject;
    }
    @Override
    public String toString() {
        return "Notification{" +
                //"notificationID=" + notificationID +
                //", status=" + status +
                ", finalContent='" + finalContent + '\'' +
                ", receiver='" + receiver + '\'' +
                ", subject='" + this.subject + "\' }";
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




}
