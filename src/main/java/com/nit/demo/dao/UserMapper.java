package com.nit.demo.dao;

import com.nit.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserMapper extends CrudRepository<User, Integer> {
    User findAllById(Integer id);
    User findAllByName(String name);
    User findAllByCount(String count);
    User findAllByCountAndPassword(String Count, String Password);
    User findAllByCountAndPasswordAndRole(String count, String password, String role);
    User save(User u);
    Integer deleteAllByIdIn(Integer[] idIn);


}
