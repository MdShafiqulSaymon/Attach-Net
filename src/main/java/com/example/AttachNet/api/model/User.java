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
    private Integer role;

    @Column
    private String number;


    public User(Integer id, String sid, String username, String batch, String dept, String email, Integer role, String number) {
        this.id = id;
        this.sid=sid;
        this.username = username;
        this.batch = batch;
        this.dept = dept;
        this.email = email;
        this.role = role;
        this.number = number;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
