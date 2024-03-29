package com.nit.demo.controller;

import com.nit.demo.model.Task;
import com.nit.demo.model.User;
import com.nit.demo.service.TaskService;
import com.nit.demo.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Resource
    private TaskService taskService;

    @RequestMapping(value = "save")
    public Map<String,Object> save(Task task, String ExecutorName, HttpSession session){
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(new Date());
        Map<String, Object> map = new HashMap<>();
        log.info(day+":"+task.getTitle()+"save...");
        task.setBeginTime(day);
        User sender = (User)session.getAttribute("session_user");
        map = taskService.titleBool(task.getTitle());
        if(!TaskUtil.TitleExist.equals(map.get(TaskUtil.Title)))
            map = taskService.save(task,sender,ExecutorName);
        return map;
    }

    @RequestMapping(value = "findAll")
    public Map<String,Object> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows,String start_date,String end_date,HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(page-1,rows, Sort.Direction.DESC,"BeginTime");
        Page<Task> taskPage = taskService.findAll(start_date,end_date,pageable);
        map.put("total",taskPage.getTotalElements());
        map.put("rows",taskPage.getContent());
        return map;
    }
    /*
    只有发送者或执行人才能修改任务
     */
    @RequestMapping(value = "setAlter")
    public Map<String,Object> setAlterId(Integer taskId,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("session_user");
        Task task = taskService.findOne(taskId);
        map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolError);
        if(task != null) {
            if(user.getId().equals(task.getSender().getId()) || user.getId().equals(task.getExecutor().getId())) {
                session.setAttribute(TaskUtil.TaskId, taskId);
                map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolSuccess);
            }
        }
        return map;
    }

    @RequestMapping(value = "getAlter")
    public Map<String,Object> getAlter(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Integer taskId = (Integer)session.getAttribute(TaskUtil.TaskId);
        Task task = null;
        map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolError);
        if(taskId != null) {
            task = taskService.findOne(taskId);
            map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolSuccess);
            map.put(TaskUtil.Task,task);
        }
        return map;
    }

    @RequestMapping(value = "alter")
    public Map<String,Object> alter(Task task, String ExecutorName, HttpSession session){
        Integer taskId = (Integer)session.getAttribute(TaskUtil.TaskId);
        Task task1 = null;
        if(taskId != null){
            task1 = taskService.findOne(taskId);
        }
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(new Date());
        Map<String, Object> map = new HashMap<>();
        log.info(day+":"+task1.getTitle()+"alter...");
        task1.setUpdateTime(day);
        task1.setTitle(task.getTitle());
        task1.setTimeLimit(task.getTimeLimit());
        User sender = task1.getSender();
        task1.setPriority(task.getPriority());
        task1.setStatement(task.getStatement());
        map = taskService.save(task1,sender,ExecutorName);
        return map;
    }

    @RequestMapping(value = "del")
    public Map<String,Object> del(Integer taskId){
        Map<String,Object> map = new HashMap<>();
        Task task = taskService.findOne(taskId);
        map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolSuccess);
        if(task != null){
            try {
                taskService.delete(taskId);
            }catch(Exception e){
                map.put(TaskUtil.TaskBool,TaskUtil.TaskBoolError);
            }
        }
        return map;
    }
}
