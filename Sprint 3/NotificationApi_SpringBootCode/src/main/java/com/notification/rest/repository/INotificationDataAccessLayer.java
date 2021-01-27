package com.notification.rest.repository;

import com.notification.rest.model.Notification;

import java.util.ArrayList;

public interface INotificationDataAccessLayer {
    public boolean sendNotification(Notification n);
    public ArrayList<Notification> readNotification(String sendingWay);
    public ArrayList<Notification> readAll();
    public ArrayList<Notification> successfulNotifications();
    public boolean deleteNotification(int notificationID) ;
    public int selectMaxId();
}
