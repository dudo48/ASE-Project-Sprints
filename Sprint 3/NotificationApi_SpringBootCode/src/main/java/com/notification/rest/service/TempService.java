package com.notification.rest.service;

import com.notification.rest.model.Notification;
import com.notification.rest.model.NotificationReqeust;
import com.notification.rest.model.Templete;
import com.notification.rest.repository.ITemplateDataAccessLayer;
import com.notification.rest.repository.INotificationDataAccessLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TempService {

    private final ITemplateDataAccessLayer database;
    private final INotificationDataAccessLayer notificationDatabase;
    @Autowired//change the Qualifier to change the database to files .
    public TempService(@Qualifier("mysql") ITemplateDataAccessLayer database, @Qualifier("noteMysql") INotificationDataAccessLayer n) {
        this.database = database;
        this.notificationDatabase = n;
    }


    public boolean addTemplate(Templete temp){
        return database.AddTemplate(temp);

    }
    public Templete getTemplate( Integer id ){
        return database.getTemplate(id);
    }

    public boolean deleteTemplate(Integer id){
        return database.deleteTemplate(id);
    }

    public boolean updateTemplate(Integer id ,Templete temp){
        return database.updateTemplate(id,temp);
    }
    public ArrayList<Templete> getAll(){
        return database.searchTemplate();
    }

    public boolean sendNotification(NotificationReqeust req){
//        if(req.getTemplateID()!=null)
//            return false;
        Templete template= database.getTemplate(req.getTemplateID());
        int id =notificationDatabase.selectMaxId();
        Notification n = req.makeNotification(template,id+1);

        notificationDatabase.sendNotification(n);
        return  true;
    }

    public ArrayList<Notification> ReadAllNotifications(){
       return notificationDatabase.readAll();
    }
    public ArrayList<Notification> ReadNotification(String s) {
        return notificationDatabase.readNotification(s);
    }

    public ArrayList<Notification> succssfullNotifications(){
        return notificationDatabase.successfulNotifications();
    }

    }
