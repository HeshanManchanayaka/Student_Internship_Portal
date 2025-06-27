package edu.icet.service;

import edu.icet.dtos.UserDto;

import java.util.List;


public interface UserService {
    Boolean existsByEmail(String email);
    UserDto saveUser(UserDto userDTO);
    UserDto getUserByEmail(String email);
    List<UserDto> getAllUsers();
    UserDto getUser(Long id);
    void deleteUser(Long id);
}
