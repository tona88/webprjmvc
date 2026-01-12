package com.sunflower.web.controller.blog;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sunflower.web.service.blog.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailController implements Controller {

    @Override
    @Nullable
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
          
        int id = 1;
        String id_ = request.getParameter("id");
        if(id_ != null && ! id_.equals(""))
            id = Integer.parseInt(id_);

        ArticleService as =  new ArticleService();
        ModelAndView mv = new ModelAndView("blog/detail");
        mv.addObject("article",as.getArticle(id));
        return mv;
    }


    
}
