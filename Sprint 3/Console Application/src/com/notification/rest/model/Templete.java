package com.notification.rest.model;


import java.io.Serializable;

public class Templete implements Serializable {

    private Integer id;
    private String content;
    private String subject;
    private LanguageEnum language;// Serializable to deal with him as a string .

    public Templete(){
    }
    public Templete(Integer id, String content,
                     String subject, LanguageEnum language) {
        this.id = id;
        this.content = content;
        this.subject = subject;
        this.language = language;
    }
    @Override
    public String toString() {
        return "Templete{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                ", language=" + language +
                '}';
    }

    //    public Templete( String content, String subject, LanguageEnum language) {
//
//        this.content = content;
//        this.subject = subject;
//        this.language = language;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}

