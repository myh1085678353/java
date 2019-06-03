package com.nit.demo.dao;

import com.nit.demo.model.Task;
import com.nit.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskMapper extends CrudRepository<Task,Integer> {

    Task save(Task task);
    Task findAllByTitle(String title);
    Task findAllById(Integer id);

    List<Task> findAll();
    Page<Task> findAll(Pageable pageable);
    Page<Task> findBySender(User sender, Pageable pageable);
}
