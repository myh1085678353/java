package com.nit.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {

//    创表时大写首字母

    private Integer id;
    private String count;
    private String name;
    private String password;
    private String role;
    private String email;
    private String RegistrationTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "Count",nullable = false)
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }

    @Column(name = "Name",nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Password",nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "Role",nullable = false)
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registration_time")
    public String getRegistrationTime() {
        return RegistrationTime;
    }
    public void setRegistrationTime(String registrationTime) {
        RegistrationTime = registrationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(count, user.count) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, name, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
