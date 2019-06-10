package com.nit.demo.service;

import com.nit.demo.dao.TaskMapper;
import com.nit.demo.dao.UserMapper;
import com.nit.demo.dao.ProjectMapper;
import com.nit.demo.model.Task;
import com.nit.demo.model.User;
import com.nit.demo.model.Project;
import com.nit.demo.util.TaskUtil;
import com.nit.demo.util.ProjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
    private static Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private TaskMapper taskDao;

    @Autowired
    private UserMapper userDao;

    @Autowired
    private ProjectMapper projectDao;

    public Map<String,Object> save(Project project, User clientName, String projectManagerName){
        User projectManager = userDao.findAllByName(projectManagerName);
        Map<String,Object> map = new HashMap<>();
        if(projectManager != null){
            map.put(ProjectUtil.projectManagerBool,ProjectUtil.projectManagerBoolSuccess);
            project.setProjectManager(projectManager);
            project.setClientName(clientName);
            log.info(project.toString());
            Project project1 = projectDao.save(project);
            if(project1.getId() == null){
                map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolError);
            }else{
                map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolSuccess);
            }
        }else{
            map.put(ProjectUtil.projectManagerBool,ProjectUtil.projectManagerBoolError);
        }
        return map;
    }
    public Map<String,Object> titleBool(String title){
        Map<String,Object> map = new HashMap<>();
        Project project = projectDao.findAllByTitle(title);
        if(project != null){
            map.put(ProjectUtil.Title,ProjectUtil.TitleExist);
        }
        return map;
    }

    public Page<Project> findAll(String start_time,String end_time,Pageable pageable){
        Page<Project> projectPage = projectDao.findAllByBeginTimeBetween(start_time,end_time,pageable);
        return projectPage;
    }

    public Project findOne(Integer id){
        Project project = projectDao.findAllById(id);
        if(project == null){
            project = null;
        }
        return project;
    }
    public void delete(Integer id){
        projectDao.deleteById(id);
    }
}