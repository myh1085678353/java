package com.nit.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "project")
public class Project {

    private Integer Id;
    private String Title;//项目名称
    private String ClientName;//客户名称
    private User ProjectManager;//项目经理
    private Integer DevelopmentNumber;//开发人数
    private String BeginTime;//立项时间
    private String UpdateTime;//更新时间
    private Integer Priority;//优先级
    private String Statement;//任务说明
    private String Completionstatus;//完成状态

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }

    @Column(name = "title",nullable = false)
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    @Column(name = "client_name")
    public String getClientName() {
        return ClientName;
    }
    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    @ManyToOne
    @JoinColumn(name = "project_manager",referencedColumnName = "name",nullable = false)
    public  User getProjectManager() {
        return ProjectManager;
    }
    public void setProjectManager(User projectManager) {
        ProjectManager = projectManager;
    }

    @Column(name = "development_Number",nullable = false)
    public  Integer getDevelopmentNumber() {
        return DevelopmentNumber;
    }
    public void setDevelopmentNumber(Integer developmentNumber) {
        DevelopmentNumber = developmentNumber;
    }

    @Column(name = "begin_time",nullable = false)
    public String getBeginTime() {
        return BeginTime;
    }
    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    @Column(name = "update_time")
    public String getUpdateTime() {
        return UpdateTime;
    }
    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    @Column(name = "priority",nullable = false)
    public Integer getPriority() {
        return Priority;
    }
    public void setPriority(Integer priority) {
        Priority = priority;
    }

    @Column(name = "statement",nullable = false)
    public String getStatement() {
        return Statement;
    }
    public void setStatement(String statement) {
        Statement = statement;
    }

    @Column(name = "Completion_status",nullable = false)
    public String getCompletionstatus() {
        return Completionstatus;
    }
    public void setCompletionstatus(String completionstatus) {
        Completionstatus = completionstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(Id, project.Id) &&
                Objects.equals(Title, project.Title) &&
                Objects.equals(ClientName, project.ClientName) &&
                Objects.equals(ProjectManager, project.ProjectManager) &&
                Objects.equals(DevelopmentNumber, project.DevelopmentNumber) &&
                Objects.equals(BeginTime, project.BeginTime) &&
                Objects.equals(UpdateTime, project.UpdateTime) &&
                Objects.equals(Priority, project.Priority) &&
                Objects.equals(Statement, project.Statement) &&
                Objects.equals(Completionstatus, project.Completionstatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Title, ClientName, ProjectManager, DevelopmentNumber, BeginTime, UpdateTime, Priority, Statement, Completionstatus);
    }

    @Override
    public String toString() {
        return "Project{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", ClientName='" + ClientName + '\'' +
                ", ProjectManager='" + ProjectManager + '\'' +
                ", DevelopmentNumber=" + DevelopmentNumber +
                ", BeginTime='" + BeginTime + '\'' +
                ", UpdateTime='" + UpdateTime + '\'' +
                ", Priority=" + Priority +
                ", Statement='" + Statement + '\'' +
                ", Completionstatus='" + Completionstatus + '\'' +
                '}';
    }
}
