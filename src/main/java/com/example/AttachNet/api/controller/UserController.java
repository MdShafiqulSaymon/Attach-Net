package com.example.AttachNet.api.controller;

import com.example.AttachNet.api.dto.UserDto;
import com.example.AttachNet.api.model.User;
import com.example.AttachNet.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping(value = "/create",produces ="application/json")
    public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto ){
        UserDto createdUser=userService.createUser(userDto);
        System.out.println(userDto);
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping(value = "/all",produces ="application/json")
    public ResponseEntity<List<UserDto>>getAll(){
        List<UserDto> users=userService.getAllUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id ,@RequestBody User userDetails){
        User updatedUser=userService.updateUser(id,userDetails);
        return ResponseEntity.ok(updatedUser);
    }

}
