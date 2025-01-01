package com.example.AttachNet.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserDto {
    // Getters and Setters for all fields
    private Integer id;

    @NotBlank(message = "User Name can't be Blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBatch() {
        return batch;
    }

    public String getDept() {
        return dept;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getSid() {
        return sid;
    }

    public String getPassword() {
        return password;
    }

    @NotBlank(message = "Batch Name can't be Blank")
    private String batch;
    private String dept;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    private String role;
    private String phone;

    // New field added
    private String sid;
    private  String password;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSid(String sid) {
        this.sid = sid;
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
