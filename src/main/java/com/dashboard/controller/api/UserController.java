package com.dashboard.controller.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.entity.User;
import com.dashboard.model.UserDto;
import com.dashboard.service.UserService;

@RestController
// @Tag(name = "USER")
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    // @Operation(summary = "Get Users", tags = { "USER" })
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(Principal principal) {
        List<User> users = userService.getAllUsers(principal);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getAllUsers(@PathVariable Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // @Operation(summary = "Create User", tags = { "USER" })
    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@Validated @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }
    
    // @Operation(summary = "Create Dummy User", tags = { "USER" })
    @PostMapping(value = "/create_dummy")
    public ResponseEntity<User> create_dummy(@Validated @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }
}
