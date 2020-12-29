package com.notification.rest.repository;

import com.notification.rest.model.Templete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface ITemplateDataAccessLayer {
    public boolean AddTemplate(Templete template);
    public boolean updateTemplate(int id, Templete template);
    public boolean deleteTemplate(int templateID);
    public Templete getTemplate(int id );
    public ArrayList<Templete> searchTemplate ();


}
