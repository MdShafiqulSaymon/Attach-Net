package com.example.AttachNet.api.controller;

import com.example.AttachNet.api.dto.LoginRequestDto;
import com.example.AttachNet.api.dto.LoginResponse;
import com.example.AttachNet.api.dto.UserDto;
import com.example.AttachNet.api.model.User;
import com.example.AttachNet.api.service.JwtService;
import com.example.AttachNet.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    JwtService jwtService;

    // Authentication endpoints Without JWT Token
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            UserDto user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok()
                .body(createResponse("Login successful", user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(createErrorResponse("Invalid credentials", e.getMessage()));
        }
    }
    // Authentication endpoints Without JWT Token
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User registeredUser = userService.signup(userDto);

        return ResponseEntity.ok(registeredUser);
    }

    // Registration endpoints With BCrypt
    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequestDto loginUserDto) {
        User authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    // User management Registration endpoints Without BCrypt
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            UserDto createdUser = userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(createResponse("User created successfully", createdUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(createErrorResponse("Failed to create user", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDto> users = userService.getAllUser();
            return ResponseEntity.ok()
                .body(createResponse("Users retrieved successfully", users));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("Failed to retrieve users", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(createResponse("User retrieved successfully", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("User not found", "No user found with ID: " + id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("Failed to retrieve user", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok()
                .body(createResponse("User updated successfully", updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(createErrorResponse("Failed to update user", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok()
                .body(createResponse("User deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(createErrorResponse("Failed to delete user", e.getMessage()));
        }
    }

    // Exception handlers
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest()
            .body(createErrorResponse("Validation failed", errors));
    }

    // Helper methods for consistent response format
    private Map<String, Object> createResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    private Map<String, Object> createErrorResponse(String message, Object errors) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        response.put("errors", errors);
        return response;
    }
}