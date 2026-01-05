package com.sunflower.web.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexController implements Controller{

    @Override
    public @Nullable ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
                ModelAndView mv = new ModelAndView();
                mv.addObject("data","Hello World");
                mv.setViewName("index");
               return mv;        
    }
    
}
