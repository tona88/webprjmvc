package com.sunflower.web.entity;

import java.util.Date;

public class ArticleView extends Article {
    
    private int commentCount;

   
    public ArticleView(int id, String title, String content,String writer, Date createAt, String files, int hit,boolean pub, int commentCount) {
       
        super(id, title, content,writer, createAt, files, hit,pub);
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    
}
