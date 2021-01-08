package com.notification.rest;

import com.notification.rest.repository.INotificationDataAccessLayer;
import com.notification.rest.repository.MysqlNotificationDataAccessLayer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);

//		INotificationDataAccessLayer test= new MysqlNotificationDataAccessLayer();
//		test.deleteTemplate(0);

	}

}
