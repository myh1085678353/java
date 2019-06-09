package com.nit.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    private Integer Id;
    private String Title;//任务主题
    private String BeginTime;//创建时间
    private String UpdateTime;
    private String TimeLimit;//工期
    private User Sender;//发送人
    private User Executor;//执行人
    private Integer Priority;//优先级
    private String Statement;//任务说明

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

    @Column(name = "time_limit",nullable = false)
    public String getTimeLimit() {
        return TimeLimit;
    }
    public void setTimeLimit(String timeLimit) {
        TimeLimit = timeLimit;
    }

    @ManyToOne
    @JoinColumn(name = "sender",referencedColumnName = "name",nullable = false)
    public User getSender() {
        return Sender;
    }
    public void setSender(User sender) {
        Sender = sender;
    }

    @ManyToOne
    @JoinColumn(name = "executor",referencedColumnName = "name",nullable = false)
    public User getExecutor() {
        return Executor;
    }
    public void setExecutor(User executor) {
        Executor = executor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(Id, project.Id) &&
                Objects.equals(Title, project.Title) &&
                Objects.equals(BeginTime, project.BeginTime) &&
                Objects.equals(TimeLimit, project.TimeLimit) &&
                Objects.equals(Sender, project.Sender) &&
                Objects.equals(Executor, project.Executor) &&
                Objects.equals(Priority, project.Priority) &&
                Objects.equals(Statement, project.Statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Title, BeginTime, TimeLimit, Sender, Executor, Priority, Statement);
    }

    @Override
    public String toString() {
        return "Project{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", BeginTime=" + BeginTime +
                ", TimeLimit='" + TimeLimit + '\'' +
                ", Sender=" + Sender +
                ", Executor=" + Executor +
                ", Priority='" + Priority + '\'' +
                ", Statement='" + Statement + '\'' +
                '}';
    }
}
