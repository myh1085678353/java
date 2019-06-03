package com.nit.demo.controller;

import com.nit.demo.model.User;
import com.nit.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/user")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/loginHtml"})
    public String loginHtml(){
        return "userLogin";
    }

    @RequestMapping(value = {"/userLogin"})
    public String Login(@RequestParam("count") String count, @RequestParam("password") String password, HttpServletRequest request){

        User user = userService.login(count,password);

        if(user != null){
            request.getSession().setAttribute("session_user",user);
            return "index";
        }
        return "loginError";
    }

    @RequestMapping(value = {"/registerpage"})
    public ModelAndView registerpage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/register.html");
        return  modelAndView;
    }

    @RequestMapping({"/uregister"})
    public String addUser(@RequestParam("count") String count,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("name") String name){
        User u = new User();
        u.setCount(count);
        u.setPassword(password);
        u.setName(name);
        u.setRole("normal");
        if(!password.equals(password2)){

            return "两次密码不相同，注册失败！！";
        }else {
            User user = userService.save(u);
            if(user == null){
                return "注册失败！";
            }else {
                return "注册成功！";
            }
        }

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
    @ResponseBody
    @RequestMapping(value = "uregister1")
    public ModelAndView addUser1(User user, HttpSession session){
        System.out.println(user.toString());

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


}