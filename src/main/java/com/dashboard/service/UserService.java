package com.dashboard.service;

import java.security.Principal;
import java.util.List;

import com.dashboard.entity.User;
import com.dashboard.model.UserDto;

public interface UserService {
    List<User> getAllUsers(Principal principal);
    User getById(Long id);
    User addUser(UserDto userDto);
    User addUserDummy(UserDto userDto);
    void deleteUser(Long id);
}
