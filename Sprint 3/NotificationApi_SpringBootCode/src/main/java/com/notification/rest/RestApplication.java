package com.notification.rest;

import com.notification.rest.repository.INotificationDataAccessLayer;
import com.notification.rest.repository.MysqlNotificationDataAccessLayer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
//		INotificationDataAccessLayer database = new MysqlNotificationDataAccessLayer();
//		ConsoleApp app = new ConsoleApp(database);
//		app.run();
//		InotificationDataAccessLayer test= new MysqlNotificationDataAccessLayer();
//		ArrayList<String> placeholders= new ArrayList<String>();
//		placeholders.add("Mohamed");
//		placeholders.add("book");
//		test.readAll();
		//NotificationReqeust x= new NotificationReqeust(placeholders,2018,"ma@gmail.com");
//		Notification n1= new Notification(2022,2023,true,null,"ma@gmail.com");
//		test.sendNotification(n1);

	}

}
