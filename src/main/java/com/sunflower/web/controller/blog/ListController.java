package com.sunflower.web.controller.blog;


import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sunflower.web.service.blog.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ListController implements Controller {

    @Override
    @Nullable
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
                
        String field = "title" ;
        String field_ = request.getParameter("f");
        if(field_ != null && ! ! field.equals(""))
            field=field_;
        
        String query = "";
        String query_ = request.getParameter("q");
        if(query_ != null && !query_.equals(""))
            query = query_;

        int page = 1;
        String page_ = request.getParameter("p");
        if(page_ != null && ! page_.equals(""))
            page = Integer.parseInt(page_);

        System.out.printf("field:%s,query:%s,page:%d\n",field,query,page);

        ModelAndView mv = new ModelAndView("blog/list");
        ArticleService articleService = new ArticleService();
        mv.addObject("articles", articleService.getPublicArticles(field, query, page));
        mv.addObject("count",articleService.getPublicArticleCount(field, query));
        return mv; 
    }

   
}
