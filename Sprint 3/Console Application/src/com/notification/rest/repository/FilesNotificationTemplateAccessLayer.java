package com.notification.rest.repository;

import com.notification.rest.model.LanguageEnum;
import com.notification.rest.model.Templete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesNotificationTemplateAccessLayer implements ITemplateDataAccessLayer{
    private ArrayList<Templete> templates;
    private final String FILE_NAME = "myData.txt";

    public FilesNotificationTemplateAccessLayer() {
        File file = new File(FILE_NAME);
        if(!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        templates = readFromFile();
        templates.add(new Templete(1, "gg", "ggg", LanguageEnum.English));
    }

//    private ArrayList<Templete> readFromFile() {
//        ArrayList<Templete> templates = new ArrayList<>();
//        try {
//            Scanner scan = new Scanner(new File(FILE_NAME));
//            while(scan.hasNext()) {
//                int id = scan.nextInt();
//                String content = scan.next();
//                String subject = scan.next();
//                String tempLanguage= scan.next();
//                tempLanguage.trim();
//
//                LanguageEnum language;
//
//                language = LanguageEnum.valueOf(tempLanguage);
//                templates.add(new Templete(id, content, subject, language));
//            }
//            scan.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return templates;
//    }

    private ArrayList<Templete> readFromFile() {
        ArrayList<Templete> templates = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(FILE_NAME));
            while(scan.hasNext()) {
                int id = scan.nextInt();
                scan.nextLine();
                String content = scan.nextLine();
                String subject = scan.nextLine();
                LanguageEnum language;
                language = LanguageEnum.valueOf(scan.nextLine());
                templates.add(new Templete(id, content, subject, language));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return templates;
    }

    private void writeToFile() {
        try {
            FileWriter writer = new FileWriter(new File(FILE_NAME));
            for(int i = 0; i < templates.size(); i++) {
                writer.write(templates.get(i).getId() + "\n" + templates.get(i).getContent() + "\n"
                        + templates.get(i).getSubject() + "\n" + templates.get(i).getLanguage());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean AddTemplate(Templete template) {
        templates = this.readFromFile();
        templates.add(template);
        writeToFile();
        return true;
    }

    @Override
    public boolean updateTemplate(int id, Templete template) {
        templates = this.readFromFile();
        for(int i = 0; i < templates.size(); i++) {
            if(templates.get(i).getId() == id) {
                templates.set(i, template);
                writeToFile();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteTemplate(int templateID) {
        templates = this.readFromFile();
        for(int i = 0; i < templates.size(); i++) {
            if(templates.get(i).getId() == templateID) {
                templates.remove(i);
                writeToFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public Templete getTemplate(int id) {
        templates = this.readFromFile();
        Templete temp = null;
        for(int i = 0; i < templates.size(); i++) {
            if(id == templates.get(i).getId()) {
                temp = templates.get(i);
            }
        }
        writeToFile();
        return temp;
    }


    @Override
    public ArrayList<Templete> searchTemplate() {
        return this.readFromFile();
    }

    public int size() {
        return templates.size();
    }

}
