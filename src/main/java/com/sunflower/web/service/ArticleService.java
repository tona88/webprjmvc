package com.sunflower.web.service;


import java.util.List;
import com.sunflower.web.entity.Article;
import com.sunflower.web.entity.ArticleView;

public interface ArticleService {

    public List<ArticleView> getArticles(int page);
    public List<ArticleView> getArticles(String field,String keyword,int page);
    public List<ArticleView> getPublicArticles(String field, String query, int page) ;
    public int getArticleCount();
    public int getArticleCount(String field,String keyword);
    public int getPublicArticleCount(String field,String query);
    public Article getArticle(int id);
    public Article getPrevArticle(int id);
    public Article getNextArticle(int id);
    public int insertArticle(Article article);
    public int deleteArticleAll(int[] ids);
    public int pubArticleAll(List<String> pids, List<String> cids);
    public int pubArticleAll(int[] pids,int[] cids);
    public int pubArticleAll(String pidCSV,String cidCSV);
    
}
