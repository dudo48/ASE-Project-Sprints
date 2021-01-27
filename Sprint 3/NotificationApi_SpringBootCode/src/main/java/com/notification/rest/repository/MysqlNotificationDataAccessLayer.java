package com.notification.rest.repository;

import com.notification.rest.model.Notification;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
@Repository ("noteMysql")
public class MysqlNotificationDataAccessLayer implements INotificationDataAccessLayer {
    Connection conn = null;

    public MysqlNotificationDataAccessLayer() {
        try {// to connect to the database .
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean sendNotification(Notification n) {
        String INSERT_NOTIFICATION = "INSERT INTO notification" + " (notificationID, status , receiver" +
                ",finalContent,subject) VALUES " +
                " (?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatment = conn.prepareStatement(INSERT_NOTIFICATION);
            preparedStatment.setInt(1, n.getNotificationID());
            preparedStatment.setBoolean(2, n.isStatus());
            preparedStatment.setString(3, n.getReceiver());
            preparedStatment.setString(4,n.getFinalContent());
            preparedStatment.setString(5,n.getSubject());

            if (preparedStatment.executeUpdate() < 1) {
                System.out.println("error in inserting ");
            } else {
                System.out.println("1 row inserted");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("connection failed");
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public ArrayList<Notification> readNotification(String reciver) {
        ArrayList<Notification> notifications = new ArrayList<>();
        String SELECT_NOTIFICATIONS_Receiver = "select * from notification where receiver = ? ";

        try {
            PreparedStatement preparedStatment = conn.prepareStatement(SELECT_NOTIFICATIONS_Receiver);
            preparedStatment.setString(1,reciver);
            ResultSet rs = preparedStatment.executeQuery();
            while (rs.next()) {
                Notification temp = new Notification(rs.getInt("notificationID"),rs.getString("finalContent"),
                        rs.getString("receiver"),rs.getString("subject"),rs.getBoolean("status"));
                notifications.add(temp);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Notification n:notifications)
            System.out.println(n.toString()+"\n");
        return notifications;
    }

    @Override
    public ArrayList<Notification> readAll() {
        String SELECT_ALL_NOTIFICATIONS = "select * from notification ";
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            Statement statment = conn.createStatement();
            ResultSet rs = statment.executeQuery(SELECT_ALL_NOTIFICATIONS);
            while (rs.next()) {
                Notification temp = new Notification(rs.getInt("notificationID"),rs.getString("finalContent"),
                        rs.getString("receiver"), rs.getString("subject"),rs.getBoolean("status"));
                notifications.add(temp);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Notification n:notifications)
            System.out.println(n.toString()+"\n");
        return notifications;
    }

    @Override
    public ArrayList<Notification> successfulNotifications() {
        ArrayList<Notification> notifications = new ArrayList<>();
        String SELECT_NOTIFICATIONS_Receiver = "select * from notification where status = true ";

        try {
            PreparedStatement preparedStatment = conn.prepareStatement(SELECT_NOTIFICATIONS_Receiver);
           // preparedStatment.setString(1,reciver);
            ResultSet rs = preparedStatment.executeQuery();
            while (rs.next()) {
                Notification temp = new Notification(rs.getInt("notificationID"),rs.getString("finalContent"),
                        rs.getString("receiver") , rs.getString("subject"),rs.getBoolean("status"));
                notifications.add(temp);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Notification n:notifications)
            System.out.println(n.toString()+"\n");
        return notifications;

    }

    @Override
    public boolean deleteNotification(int notificationID) {
        String DELETE_NOTIFICATION = "delete from notification where notificationID = ?;";

        try {
            PreparedStatement preparedStatment = conn.prepareStatement(DELETE_NOTIFICATION);
            preparedStatment.setInt(1, notificationID);

            int x = preparedStatment.executeUpdate();
            if (x < 1) {
                System.out.println("this id [" + notificationID + "] is not exist \n Deletion failed");
                return false;
            } else
                System.out.println("1 Notification deleted");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int selectMaxId() {
        String selectMax="select max(notificationID) from notification";
        int mxID=0;
        try {
            Statement statment = conn.createStatement();
            ResultSet rs = statment.executeQuery(selectMax);
            rs.next();
            mxID= rs.getInt(1);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return mxID;
    }

}
