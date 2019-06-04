package com.nit.demo.service;

import com.nit.demo.dao.TaskMapper;
import com.nit.demo.dao.UserMapper;
import com.nit.demo.model.Task;
import com.nit.demo.model.User;
import com.nit.demo.util.TaskUtil;
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
public class TaskService {
    private static Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskMapper taskDao;

    @Autowired
    private UserMapper userDao;

    public Map<String,Object> save(Task task, User sender, String executorName){
        User executor = userDao.findAllByName(executorName);
        Map<String,Object> map = new HashMap<>();
        if(executor != null){
            map.put(TaskUtil.executorBool,TaskUtil.executorBoolSuccess);
            task.setExecutor(executor);
            task.setSender(sender);
            log.info(task.toString());
            Task task1 = taskDao.save(task);
            if(task1.getId() == null){
                map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolError);
            }else{
                map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolSuccess);
            }
        }else{
            map.put(TaskUtil.executorBool,TaskUtil.executorBoolError);
        }
        return map;
    }
    public Map<String,Object> titleBool(String title){
        Map<String,Object> map = new HashMap<>();
        Task task = taskDao.findAllByTitle(title);
        if(task != null){
            map.put(TaskUtil.Title,TaskUtil.TitleExist);
        }
        return map;
    }

    public Page<Task> findAll(String start_time,String end_time,Pageable pageable){
        Page<Task> taskPage = taskDao.findAllByBeginTimeBetween(start_time,end_time,pageable);
        return taskPage;
    }

    public Task findOne(Integer id){
        Task task = taskDao.findAllById(id);
        if(task == null){
            task = null;
        }
        return task;
    }
    public void delete(Integer id){
        taskDao.deleteById(id);
    }
}
