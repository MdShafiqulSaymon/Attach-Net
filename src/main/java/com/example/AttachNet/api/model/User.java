package com.example.AttachNet.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String sid;

    @Column(nullable = false)
    private String username;

    @Column
    private String batch;

    @Column
    private String dept;

    @Column(unique = true)
    private String email;

    @Column
    private String role;

    @Column
    private String phone;
    @Column
    private  String password;


    public User(Integer id, String sid, String username, String batch, String dept, String email, String role, String phone,String password) {
        this.id = id;
        this.sid=sid;
        this.username = username;
        this.batch = batch;
        this.dept = dept;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.password=password;
    }

    // Default Constructor
    public User() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "UserEntity{" +
               "sid='" + sid + '\'' +
               ", username='" + username + '\'' +
               ", batch='" + batch + '\'' +
               ", dept='" + dept + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               ", phone='" + phone + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
