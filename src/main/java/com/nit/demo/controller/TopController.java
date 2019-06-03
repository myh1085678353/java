package com.nit.demo.controller;

import com.nit.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/top")
public class TopController {
    private static Logger log = LoggerFactory.getLogger(TopController.class);

    @RequestMapping(value = "login")
    public ModelAndView login(){
        log.info("跳转登陆界面");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/userLogin.html");
        return modelAndView;
    }
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public String user(HttpSession session){
        log.info("获取用户信息");
        User u = (User)session.getAttribute("session_user");
        if(u != null)
            log.info("user:"+u.toString());
        else {
            log.info("user is not login");
            return "error";
        }
        return u.getName();
    }
}
