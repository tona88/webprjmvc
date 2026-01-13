package com.sunflower.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunflower.web.service.ArticleService;

@Controller
public class HomeController{

    @Autowired
    private ArticleService articleService = null;

    @RequestMapping(value="/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/index");
        mv.addObject("title","선플라워에 오신걸 환영합니다.");
        return mv;   
        
    }
   
}
