package com.nit.demo.controller;

import com.nit.demo.model.Project;
import com.nit.demo.model.User;
import com.nit.demo.service.ProjectService;
import com.nit.demo.util.ProjectUtil;
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
@RequestMapping(value = "/project")
public class ProjectController {
    private static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "save")
    public Map<String,Object> save(Project project, String ProjectManagerName, HttpSession session){
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(new Date());
        Map<String, Object> map = new HashMap<>();
        log.info(day+":"+project.getTitle()+"save...");
        project.setBeginTime(day);
        User clientName = (User)session.getAttribute("session_user");
        map = projectService.titleBool(project.getTitle());
        if(!ProjectUtil.TitleExist.equals(map.get(ProjectUtil.Title)))
            map = projectService.save(project,clientName,ProjectManagerName);
        return map;
    }

    @RequestMapping(value = "findAll")
    public Map<String,Object> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows,String start_date,String end_date,HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(page-1,rows, Sort.Direction.DESC,"BeginTime");
        Page<Project> projectPage = projectService.findAll(start_date,end_date,pageable);
        map.put("total",projectPage.getTotalElements());
        map.put("rows",projectPage.getContent());
        return map;
    }
    /*
    只有发送者或执行人才能修改任务
     */
    @RequestMapping(value = "setAlter")
    public Map<String,Object> setAlterId(Integer taskId,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User)session.getAttribute("session_user");
        Project project = projectService.findOne(taskId);
        map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolError);
        if(project != null) {
            if(user.getId().equals(project.getClientName().getId()) || user.getId().equals(project.getProjectManager().getId())) {
                session.setAttribute(ProjectUtil.ProjectId, taskId);
                map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolSuccess);
            }
        }
        return map;
    }

    @RequestMapping(value = "getAlter")
    public Map<String,Object> getAlter(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        Integer projectId = (Integer)session.getAttribute(ProjectUtil.ProjectId);
        Project project = null;
        map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolError);
        if(projectId != null) {
            project = projectService.findOne(projectId);
            map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolSuccess);
            map.put(ProjectUtil.Project,project);
        }
        return map;
    }

    @RequestMapping(value = "alter")
    public Map<String,Object> alter(Project project, String ProjectManagerName, HttpSession session){
        Integer projectId = (Integer)session.getAttribute(ProjectUtil.ProjectId);
        Project project1 = null;
        if(projectId != null){
            project1 = projectService.findOne(projectId);
        }
        SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(new Date());
        Map<String, Object> map = new HashMap<>();
        log.info(day+":"+project1.getTitle()+"alter...");
        project1.setUpdateTime(day);
        project1.setTitle(project.getTitle());
        User clientName = project1.getClientName();
        project1.setPriority(project.getPriority());
        project1.setStatement(project.getStatement());
        map = projectService.save(project1,clientName,ProjectManagerName);
        return map;
    }

    @RequestMapping(value = "del")
    public Map<String,Object> del(Integer projectId){
        Map<String,Object> map = new HashMap<>();
        Project project = projectService.findOne(projectId);
        map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolSuccess);
        if(project != null){
            try {
                projectService.delete(projectId);
            }catch(Exception e){
                map.put(ProjectUtil.ProjectBool,ProjectUtil.ProjectBoolError);
            }
        }
        return map;
    }
}