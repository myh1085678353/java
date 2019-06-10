package com.nit.demo.service;

import com.nit.demo.dao.UserMapper;
import com.nit.demo.model.Task;
import com.nit.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userDao;

    /*
    login by 苗益辉
     */
    public User login1(User user){
        User u = null;
        if(!"".equals(user.getCount()) && !"".equals(user.getPassword()) && !"".equals(user.getRole())){
            log.info("登陆中...");
            u = userDao.findAllByCountAndPasswordAndRole(user.getCount(),user.getPassword(),user.getRole());
        }
        return u;
    }
    public User save(User user){
        User u = null;
        if(!"".equals(user.getCount()) && !"".equals(user.getPassword()) && !"".equals(user.getRole()) && !"".equals(user.getName())){
            u = userDao.findAllByCount(user.getCount());
            if(u == null) {
                log.info("注册中...");
                u = userDao.save(user);
            }else
                u = null;
        }
        return u;
    }
    public User save1(User user){
        User u = null;
        if(!"".equals(user.getCount()) && !"".equals(user.getPassword()) && !"".equals(user.getRole()) && !"".equals(user.getName())){
            log.info("修改中...");
            u = userDao.save(user);
        }
        return u;
    }
    public User findOne(Integer id){
        User u = userDao.findAllById(id);
        log.info(u.toString());
        return u;
    }
}
