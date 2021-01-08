package com.notification.rest.contoller;

import com.notification.rest.model.Notification;
import com.notification.rest.model.NotificationReqeust;
import com.notification.rest.model.Templete;
import com.notification.rest.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class TemplateController {
private final TempService templateTempService;

    @Autowired
    public TemplateController(TempService templateTempService) {
        this.templateTempService = templateTempService;
    }

    @PostMapping
    void addTemplate(@RequestBody Templete temp){
        System.out.println(temp.toString());
        templateTempService.addTemplate(temp);
    }
    @GetMapping("/template/all")
    ArrayList<Templete> getAllTemplates(){
       return templateTempService.getAll();
    }
    @GetMapping("/template/{id}")
    Templete getTemplate(@PathVariable("id") Integer id){
        return templateTempService.getTemplate(id);
    }
    @PutMapping("/template/{id}")
    public boolean updateTemplate(@PathVariable Integer id ,@RequestBody Templete temp) {
        return  templateTempService.updateTemplate(id,temp);
    }
    @DeleteMapping ("/template/{id}")
    public boolean deleteTemplate (@PathVariable Integer id )
    {
        return templateTempService.deleteTemplate(id);
    }

    @PostMapping("/note")
    boolean addNotification (@RequestBody NotificationReqeust req){
        templateTempService.sendNotification(req);
        return true;
    }
    @GetMapping("/note/all")
    ArrayList<Notification> getAllNotification(){
        return templateTempService.ReadAllNotifications();
    }
    @GetMapping("/note/{receiver}")
    ArrayList<Notification> getNotification(@PathVariable String receiver){
        //System.out.println("\n\n\n\n  heloo\n\n\n\n "+receiver);
        return templateTempService.ReadNotification(receiver);
    }
    @GetMapping("/note/alls")
    ArrayList<Notification> getSuccessfullNotification(){
        return templateTempService.succssfullNotifications();
    }
}
