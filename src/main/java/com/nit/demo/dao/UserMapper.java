package com.nit.demo.dao;

import com.nit.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserMapper extends JpaRepository<User, Integer> {
    User findAllById(Integer id);
    User findAllByCount(String count);
    User findAllByCountAndPassword(String Count, String Password);
    User findAllByCountAndPasswordAndRole(String count, String password, String role);
    User save(User u);
    Integer deleteAllByIdIn(Integer[] idIn);

}
