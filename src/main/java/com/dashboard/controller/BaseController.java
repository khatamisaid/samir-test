package com.dashboard.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.dashboard.model.UserDto;
import com.dashboard.service.UserService;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;


    @GetMapping("/dummy")
    public ResponseEntity<String> dummy() {
        UserDto user = new UserDto();
        user.setName("Admin");
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setRole(1L);
        user.setPassword("admin");
        userService.addUserDummy(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/swagger-ui")
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping("/swagger-ui/")
    public String swaggerr() {
        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping("/list_users")
    public String list_users(Model model, Principal principal) {
        model.addAttribute("addUser", new UserDto());
        model.addAttribute("fragmentName", "list_users");
        model.addAttribute("users", userService.getAllUsers(principal));
        return "index";
    }

    @PostMapping(value = "/add_user", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public String add_user(@ModelAttribute UserDto userDto, Model model, Principal principal) {
        userDto.setRole(2L);
        userService.addUser(userDto);
        model.addAttribute("fragmentName", "list_users");
        model.addAttribute("users", userService.getAllUsers(principal));
        return "redirect:/list_users";
    }

    @DeleteMapping(value = "/delete_user/{id}")
    public ResponseEntity<String> delete_user(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Berhasil Di hapus", HttpStatus.OK);
    }

}
