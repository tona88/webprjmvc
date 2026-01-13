package com.sunflower.web.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sunflower.web.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DetailController{

    @Autowired
    private ArticleService articleService = null;
    
    @RequestMapping(value="/blog/detail")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
          
        int id = 1;
        String id_ = request.getParameter("id");
        if(id_ != null && ! id_.equals(""))
            id = Integer.parseInt(id_);

        
        ModelAndView mv = new ModelAndView("views/blog/detail");
        mv.addObject("article",articleService.getArticle(id));
        return mv;
    }

    
}
