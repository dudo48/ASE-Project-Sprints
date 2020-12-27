package org.notify.notification_api.infrastructure;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.notify.notification_api.model.LanguageEnum;
import org.notify.notification_api.model.NotificationTemplate;

public class DatabaseNotificationTemplateAccessLayer
							implements INotificationTemplateDataAccessLayer{
	Connection conn = null;
	int maxID;
	public DatabaseNotificationTemplateAccessLayer() {
		try {// to connect to the database .
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.maxID = 0;
	}
	
	@Override
	public boolean AddTemplate(NotificationTemplate template) {
		 template.setId(maxID + 1);
		 this.maxID ++;
		 String INSERT_NOTIFICATION = "INSERT INTO templates" + " (content, temp_id, subject, language) VALUES " +
			        " (?, ?, ?, ?);" ; 
		 try {
				PreparedStatement preparedStatment= conn.prepareStatement(INSERT_NOTIFICATION);
				preparedStatment.setString(1, template.getContent());
				preparedStatment.setInt(2, template.getId());
				preparedStatment.setString(3,template.getSubject() );
				preparedStatment.setString(4,template.getLanguage().toString());
								
				if( preparedStatment.executeUpdate()<1 ) {
					System.out.println("error in inserting ");
				}
				else { System.out.println("1 row inserted");
					return true;
				}
		} catch (SQLException e) {
			System.out.println("connection failed");
			e.printStackTrace();
		}
			
		
		return false;
	}

	@Override
	public boolean updateTemplate(int id, NotificationTemplate template) {
		 String UPDATE_NOTIFICATION="update templates set content = ? ,temp_id = ?,"
					+ "subject = ? , language = ?  where temp_id = ? ;";
		try {
			PreparedStatement preparedStatment= conn.prepareStatement(UPDATE_NOTIFICATION);
			// (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone)
			preparedStatment.setString(1,template.getContent());
			preparedStatment.setInt(2, template.getId());
			preparedStatment.setString(3, template.getSubject());
			preparedStatment.setString(4, template.getLanguage().toString());
			preparedStatment.setInt(5, template.getId());
			System.out.println(preparedStatment);
			
			if (preparedStatment.executeUpdate()<1) {
				System.out.println("this id "+ template.getId() +" is not exist \n update failed");
				return false ;
			}
			else {System.out.println("1 row updated");
				return true;
			}
		} catch (SQLException e) {			
			  e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTemplate(int templateID) {
		String DELETE_NOTIFICATION ="delete from templates where temp_id = ?;";
		
		try {
			PreparedStatement preparedStatment = conn.prepareStatement(DELETE_NOTIFICATION);
			preparedStatment.setInt(1, templateID);
			
		int x=	preparedStatment.executeUpdate();
		if (x<1) { 
			System.out.println("this id ["+templateID +"] is not exist \n Deletion failed");
			return false;
		}else 
			System.out.println("1 row deleted");
		return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public NotificationTemplate getTemplate(int id) {
		String SELECT_NOTIFICATION = "select * from templates where temp_id = ?";
		NotificationTemplate returnedObj= null;
		try {
			PreparedStatement preparedStatment = conn.prepareStatement(SELECT_NOTIFICATION);
			System.out.println(preparedStatment);
			preparedStatment.setInt(1, id);
			// execute the query or update the query  
			ResultSet rs = preparedStatment.executeQuery();	
			//process the result set to get the notification .
			while(rs.next()) {
				// to get the Attrubites of the notification into  notification object. 	
				//  (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone)
				LanguageEnum templanguage = LanguageEnum.valueOf(rs.getString("language"));
				returnedObj = new NotificationTemplate(rs.getInt("temp_id"),rs.getString("content"),rs.getString("subject"),templanguage  );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (returnedObj==null) {
			System.out.println("this id doesn not exist NULL returned ");
		}
		return returnedObj;
		
	}

	@Override
	public ArrayList<NotificationTemplate> searchTemplate() {
		String SELECT_ALL_NOTIFICATIONS="select * from templates ";
		ArrayList<NotificationTemplate> notifications= new ArrayList<>();
		try {
			Statement statment = conn.createStatement();
			ResultSet rs =statment.executeQuery(SELECT_ALL_NOTIFICATIONS) ;
			while (rs.next()) {
				LanguageEnum tempLanguage= LanguageEnum.valueOf(rs.getString("language"));
				NotificationTemplate temp= new NotificationTemplate(rs.getInt("temp_id"),rs.getString("content"),rs.getString("subject"),tempLanguage  );
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
	
	public static void main(String[] args) {
		INotificationTemplateDataAccessLayer test = new DatabaseNotificationTemplateAccessLayer();
		System.out.println( test.getTemplate(1).toString() );
		// System.out.println((test.getTemplate(3)).toString() );// get yemplate test 
	}

}
