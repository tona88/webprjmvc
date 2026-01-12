package com.sunflower.web.entity;

import java.util.Date;

public class Article {
    
    private int id;
    private String title;
    private String content;
    private String writer;
    private Date createAt;
    private String files;
    private int hit;
    private boolean pub;    
    
   
    public Article() {
    }
    public Article(String title, String content, String writer, boolean pub,String files) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.pub = pub;
        this.files = files;
    }
    public Article(int id, String title, String content, String writer, Date createAt, String files, int hit,
            boolean pub) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createAt = createAt;
        this.files = files;
        this.hit = hit;
        this.pub = pub;
    }
    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
    }
 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public String getFiles() {
        return files;
    }
    public void setFiles(String files) {
        this.files = files;
    }
    public boolean getPub() {
        return pub;
    }
    public void setPub(boolean pub) {
        this.pub = pub;
    }
    
    
}
