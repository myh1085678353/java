package com.nit.demo.dao;

import com.nit.demo.model.Project;
import com.nit.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProjectMapper extends CrudRepository<Project,Integer> {

    Project save(Project project);
    Project findAllByTitle(String title);
    Project findAllById(Integer id);
    Project findAllByClientName(String clientName);
    Project findAllByProjectManager(String projectManager);

    List<Project> findAll();
    Page<Project> findAllByBeginTimeBetween(String start_time,String end_tme,Pageable pageable);
    Page<Project> findByClientName(User clientName, Pageable pageable);

    void deleteById(Integer id);
}