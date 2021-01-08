package com.notification.rest.repository;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.notification.rest.model.LanguageEnum;
import com.notification.rest.model.Templete;
import org.springframework.stereotype.Repository;

@Repository("mysql")
public class MysqlTemplatesDataAccessLayer
        implements ITemplateDataAccessLayer {
    Connection conn = null;

    public MysqlTemplatesDataAccessLayer() {
        try {// to connect to the database .
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean AddTemplate(Templete template) {
        String INSERT_NOTIFICATION = "INSERT INTO templates" + " (content, temp_id, subject, language) VALUES " +
                " (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatment = conn.prepareStatement(INSERT_NOTIFICATION);
            preparedStatment.setString(1, template.getContent());
            preparedStatment.setInt(2, template.getId());
            preparedStatment.setString(3, template.getSubject());
            preparedStatment.setString(4, template.getLanguage().toString());

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
    public boolean updateTemplate(int id, Templete template) {
        String UPDATE_NOTIFICATION = "update templates set content = ? ,temp_id = ?,"
                + "subject = ? , language = ?  where temp_id = ? ;";
        try {
            PreparedStatement preparedStatment = conn.prepareStatement(UPDATE_NOTIFICATION);
            // (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone)
            preparedStatment.setString(1, template.getContent());
            preparedStatment.setInt(2, template.getId());
            preparedStatment.setString(3, template.getSubject());
            preparedStatment.setString(4, template.getLanguage().toString());
            preparedStatment.setInt(5, template.getId());
            System.out.println(preparedStatment);

            if (preparedStatment.executeUpdate() < 1) {
                System.out.println("this id " + template.getId() + " is not exist \n update failed");
                return false;
            } else {
                System.out.println("1 row updated");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTemplate(int templateID) {
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
    }

    @Override
    public Templete getTemplate(int id) {
        String SELECT_NOTIFICATION = "select * from templates where temp_id = ?";
        Templete returnedObj = null;
        try {
            PreparedStatement preparedStatment = conn.prepareStatement(SELECT_NOTIFICATION);
            System.out.println(preparedStatment);
            preparedStatment.setInt(1, id);
            // execute the query or update the query
            ResultSet rs = preparedStatment.executeQuery();
            //process the result set to get the notification .
            while (rs.next()) {
                // to get the Attrubites of the notification into  notification object.
                //  (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone)
                LanguageEnum templanguage = LanguageEnum.valueOf(rs.getString("language"));
                returnedObj = new Templete(rs.getInt("temp_id"), rs.getString("content"), rs.getString("subject"), templanguage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (returnedObj == null) {
            System.out.println("this id doesn not exist NULL returned ");
        }
        return returnedObj;

    }

    @Override
    public ArrayList<Templete> searchTemplate() {
        String SELECT_ALL_NOTIFICATIONS = "select * from templates ";
        ArrayList<Templete> notifications = new ArrayList<>();
        try {
            Statement statment = conn.createStatement();
            ResultSet rs = statment.executeQuery(SELECT_ALL_NOTIFICATIONS);
            while (rs.next()) {
                LanguageEnum tempLanguage = LanguageEnum.valueOf(rs.getString("language"));
                Templete temp = new Templete(rs.getInt("temp_id"), rs.getString("content"), rs.getString("subject"), tempLanguage);
                notifications.add(temp);

            }

//			for (NotificationTemplate n:notifications) {
//				System.out.println(n.toString()+"\n\n");
//			}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}