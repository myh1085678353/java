package com.nit.demo.controller;

import com.nit.demo.model.Task;
import com.nit.demo.model.User;
import com.nit.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/registerpage"})
    public ModelAndView registerpage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/register.html");
        return  modelAndView;
    }

    /*
    login by 苗益辉
     */
    @RequestMapping(value = "userLogin1")
    public ModelAndView Login1(User user, HttpSession session){
        System.out.println(user.toString());

        User u = userService.login1(user);
        ModelAndView modelAndView = new ModelAndView();
        if(u != null){
            log.info("登陆成功!");
            session.setAttribute("session_user", u);
            modelAndView.setViewName("redirect:/home.html");
        }else{
            log.info("登陆失败!");
            modelAndView.setViewName("redirect:/userLogin.html?state=error");
        }
        return modelAndView;

    }

    @RequestMapping(value = "uregister1")
    public ModelAndView addUser1(User user, HttpSession session){
        System.out.println(user.toString());
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(new Date());
        user.setRegistrationTime(day);
        User u = userService.save1(user);
        ModelAndView modelAndView = new ModelAndView();
        if(u != null) {
            log.info(u.getCount()+"注册成功！");
            session.setAttribute("session_user", u);
            modelAndView.setViewName("redirect:/home.html");
        }else{
            log.info("该账号已存在!");
            modelAndView.setViewName("redirect:/register.html?state=error");
        }
        return  modelAndView;
    }

    @RequestMapping(value = "show")
    public User show(HttpSession session){
        User user = (User)session.getAttribute("session_user");
        User u = userService.findOne(user.getId());
        return u;
    }

    @RequestMapping(value = "save")
    public String save(User user,HttpSession session){
        User u = (User)session.getAttribute("session_user");
        u.setPassword(user.getPassword());
        u.setName(user.getName());
        u.setEmail(user.getEmail());

        User user1 = userService.save1(u);
        if(u.getId().equals(user1.getId())){
            return "success";
        }
        return "failed";
    }
}