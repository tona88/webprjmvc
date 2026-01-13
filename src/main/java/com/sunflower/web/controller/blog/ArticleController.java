package com.sunflower.web.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunflower.web.service.ArticleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/blog/")
public class ArticleController {
    
     @Autowired
    private ArticleService articleService = null;
    
    @RequestMapping(value="list")
    public ModelAndView list(HttpServletRequest req,HttpServletResponse res){
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
                
        String field = "title" ;
        String field_ = req.getParameter("f");
        if(field_ != null && ! ! field.equals(""))
            field=field_;
        
        String query = "";
        String query_ = req.getParameter("q");
        if(query_ != null && !query_.equals(""))
            query = query_;

        int page = 1;
        String page_ = req.getParameter("p");
        if(page_ != null && ! page_.equals(""))
            page = Integer.parseInt(page_);

        System.out.printf("field:%s,query:%s,page:%d\n",field,query,page);

        ModelAndView mv = new ModelAndView("views/blog/list");
        mv.addObject("articles", articleService.getPublicArticles(field, query, page));
        mv.addObject("count",articleService.getPublicArticleCount(field, query));
        return mv; 
    } 

        @RequestMapping(value="detail")
        public ModelAndView detail(HttpServletRequest request, HttpServletResponse response){
          
        int id = 1;
        String id_ = request.getParameter("id");
        if(id_ != null && ! id_.equals(""))
            id = Integer.parseInt(id_);

        
        ModelAndView mv = new ModelAndView("views/blog/detail");
        mv.addObject("article",articleService.getArticle(id));
        return mv;
    }

}
