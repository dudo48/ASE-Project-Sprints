package com.notification.rest.service;

import com.notification.rest.model.Templete;
import com.notification.rest.repository.ITemplateDataAccessLayer;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TemplateService {

    private final ITemplateDataAccessLayer database;
    @Autowired//change the Qualifier to change the database to files .
    public TemplateService(@Qualifier("mysql") ITemplateDataAccessLayer database) {
        this.database = database;
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
}
