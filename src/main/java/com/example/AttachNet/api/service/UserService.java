package com.example.AttachNet.api.service;

import com.example.AttachNet.api.dto.LoginRequestDto;
import com.example.AttachNet.api.dto.UserDto;
import com.example.AttachNet.api.model.User;
import com.example.AttachNet.api.repository.UserRepo;
import com.example.AttachNet.api.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    public User signup(UserDto input) {
        User user = new User()
                .setId(input.getId())  // Assuming input has getId() method
                .setSid(input.getSid())
                .setUsername(input.getUsername())
                .setBatch(input.getBatch())  // Assuming input has getBatch() method
                .setDept(input.getDept())  // Assuming input has getDept() method
                .setEmail(input.getEmail())
                .setRole(input.getRole())  // Assuming input has getRole() method
                .setPhone(input.getPhone())  // Assuming input has getPhone() method
                .setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepo.save(user);
    }
    public User authenticate(LoginRequestDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepo.findByEmail(input.getEmail())
                .orElseThrow();
    }
    public UserDto createUser(UserDto userDto){
        User user=userTransformer.convertToEntity(userDto);
        User saveUser=userRepo.save(user);
        return userTransformer.convertToDTO(saveUser);
    }
    public List<UserDto> getAllUser(){
        List<User> userList=userRepo.findAll();
        return userTransformer.convertToDTOList((userList));
    }
    public Optional<User> getUserById(Integer id){
        return userRepo.findById(id);
    }
    public User updateUser(Integer id, User userDetails) {
        return userRepo.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setSid(userDetails.getSid());
            user.setBatch(userDetails.getBatch());
            user.setDept(userDetails.getDept());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            user.setPhone(userDetails.getPhone());
            user.setPassword(userDetails.getPassword());
            return userRepo.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
    public UserDto login(String email, String password) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        
        // Check password
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        
        // Convert to DTO and return
        return userTransformer.convertToDTO(user);
    }
}
