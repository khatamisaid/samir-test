package com.dashboard.service.impl;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashboard.entity.Role;
import com.dashboard.entity.User;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.model.UserDto;
import com.dashboard.repository.RoleRepository;
import com.dashboard.repository.UserRepository;
import com.dashboard.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> getAllUsers(Principal principal) {
        // TODO Auto-generated method stub
        ArrayList<String> list = new ArrayList<>();
        list.add("admin");list.add(principal.getName());
        return userRepository.findAll().stream().filter(dt->!list.contains(dt.getUsername())).toList();
    }

    @Override
    public User addUser(UserDto userDto) {
        Role role = roleRepository.findById(userDto.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role Not Available"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        // TODO Auto-generated method stub
        User user = modelMapper.map(userDto, User.class);
        try {
            user.setFoto(Base64.encodeBase64String(userDto.getFoto().getBytes()));
            user.setExtension(FilenameUtils.getExtension(userDto.getFoto().getOriginalFilename()));
        } catch (IOException | NullPointerException e) {
            user.setFoto(null);
            user.setExtension(null);
        }
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public User addUserDummy(UserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()){
            deleteUser(userRepository.findByUsername(userDto.getUsername()).get().getId());
        }
        Role role = roleRepository.findById(userDto.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role Not Available"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        // TODO Auto-generated method stub
        User user = modelMapper.map(userDto, User.class);
        try {
            user.setFoto(Base64.encodeBase64String(userDto.getFoto().getBytes()));
            user.setExtension(FilenameUtils.getExtension(userDto.getFoto().getOriginalFilename()));
        } catch (IOException | NullPointerException e) {
            user.setFoto(null);
            user.setExtension(null);
        }

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        User tempUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Role role = roleRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
        tempUser.getRoles().remove(role);
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user.setPassword(null);
        user.setFoto(null);
        return user;
    }

}
