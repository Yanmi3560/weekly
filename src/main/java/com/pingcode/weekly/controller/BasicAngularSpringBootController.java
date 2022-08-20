package com.pingcode.weekly.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicAngularSpringBootController {

    @RequestMapping("/")
    public ModelAndView basicAngularSpringBootTemplate() {

        return new ModelAndView("basic_angular_springboot");

    }

}

