package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.services.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @GetMapping("/secure/article-details")
    public ModelAndView getAllUserArticles() {
        final ModelAndView mav = new ModelAndView();
        mav.addObject("users", service.getAllUsers());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/unauthorized")
    public ModelAndView error() {
        final ModelAndView mav = new ModelAndView();
        final String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
