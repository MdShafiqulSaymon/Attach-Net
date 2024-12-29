package com.example.AttachNet.api.transformer;

import com.example.AttachNet.api.model.User;
import com.example.AttachNet.api.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformer {

    // Convert User entity to UserDTO
    public UserDto convertToDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setBatch(user.getBatch());
        userDTO.setDept(user.getDept());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setNumber(user.getNumber());
        userDTO.setSid(user.getSid());  // Include sid in the conversion
        return userDTO;
    }

    // Convert UserDTO to User entity
    public User convertToEntity(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setBatch(userDTO.getBatch());
        user.setDept(userDTO.getDept());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setNumber(userDTO.getNumber());
        user.setSid(userDTO.getSid());  // Include sid in the conversion
        return user;
    }
    public List<UserDto> convertToDTOList(List<User> users) {
        return users.stream()
                .map(this::convertToDTO)  // Convert each User to UserDTO
                .collect(Collectors.toList());  // Collect results into a list
    }
}