package com.notification.rest.contoller;

import com.notification.rest.model.Templete;
import com.notification.rest.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/template")
public class TemplateController {
private final TemplateService templateService;
    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }
    @PostMapping
    void addTemplate(@RequestBody Templete temp){
        System.out.println(temp.toString());
        templateService.addTemplate(temp);
    }
    @GetMapping("/all")
    ArrayList<Templete> getAllTemplates(){
       return templateService.getAll();
    }
    @GetMapping("/{id}")
    Templete getTemplate(@PathVariable("id") Integer id){
        return templateService.getTemplate(id);
    }
    @PutMapping("/{id}")
    public boolean updateTemplate(@PathVariable Integer id ,@RequestBody Templete temp) {
        return  templateService.updateTemplate(id,temp);
    }
    @DeleteMapping ("/{id}")
    public boolean deleteTemplate (@PathVariable Integer id )
    {
        return templateService.deleteTemplate(id);
    }
}
