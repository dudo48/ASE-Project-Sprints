package com.notification.rest.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "templates")
public class Templete implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "temp_id")
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "subject")
    private String subject;
    @Column(name = "language")
    @Enumerated (EnumType.STRING)
    private LanguageEnum language;// Serializable to deal with him as a string .

    public Templete(){
    }
    public Templete(@JsonProperty("id") Integer id,@JsonProperty("content") String content,
                    @JsonProperty("subject") String subject, @JsonProperty("language") LanguageEnum language) {
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

