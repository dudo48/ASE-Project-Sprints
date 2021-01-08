package com.notification.rest.repository;

import com.notification.rest.model.Notification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public interface INotificationDataAccessLayer {
    public boolean sendNotification(Notification n);
    public ArrayList<Notification> readNotification(String sendingWay);
    public ArrayList<Notification> readAll();
    public ArrayList<Notification> successfulNotifications();
    public int selectMaxId();
    public boolean deleteTemplate(int templateID) ;/*
        String DELETE_NOTIFICATION = "delete from templates where temp_id = ?;";

        try {
            PreparedStatement preparedStatment = conn.prepareStatement(DELETE_NOTIFICATION);
            preparedStatment.setInt(1, templateID);

            int x = preparedStatment.executeUpdate();
            if (x < 1) {
                System.out.println("this id [" + templateID + "] is not exist \n Deletion failed");
                return false;
            } else
                System.out.println("1 row deleted");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
