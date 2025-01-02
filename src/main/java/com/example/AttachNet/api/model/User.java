package com.example.AttachNet.api.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
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
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Constructor with parameters
    public User(Integer id, String sid, String username, String batch, String dept, String email, String role, String phone, String password) {
        this.id = id;
        this.sid = sid;
        this.username = username;
        this.batch = batch;
        this.dept = dept;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.password = password;
    }

    // Default Constructor
    public User() {
    }

    // Getters and Setters with Chaining
    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSid() {
        return sid;
    }

    public User setSid(String sid) {
        this.sid = sid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getBatch() {
        return batch;
    }

    public User setBatch(String batch) {
        this.batch = batch;
        return this;
    }

    public String getDept() {
        return dept;
    }

    public User setDept(String dept) {
        this.dept = dept;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
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
