package edu.icet.service;

import edu.icet.dtos.UserDto;


public interface UserService {
    Boolean existsByEmail(String email);
    UserDto saveUser(UserDto userDTO);
    UserDto getUserByEmail(String email);
}
