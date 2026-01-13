package com.sunflower.web.controller.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sunflower.web.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ListController {
    
    @Autowired
    private ArticleService articleService = null;
    
    @RequestMapping(value="/blog/list")
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

        ModelAndView mv = new ModelAndView("views/blog/list");
        mv.addObject("articles", articleService.getPublicArticles(field, query, page));
        mv.addObject("count",articleService.getPublicArticleCount(field, query));
        return mv; 
    }
   
}
