package com.dashboard.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.entity.Role;
import com.dashboard.exception.ResourceNotFoundException;
import com.dashboard.model.RoleDto;
import com.dashboard.repository.RoleRepository;
import com.dashboard.service.RoleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        // TODO Auto-generated method stub
        return roleRepository.findAll();
    }

    @Override
    public Role addRole(RoleDto role) {
        // TODO Auto-generated method stub
        Role roles = modelMapper.map(role, Role.class);
        return roleRepository.save(roles);

    }

    @Override
    public Role updateRole(String roleName, String tempRole) {
        // TODO Auto-generated method stub
        Role role = roleRepository.findByName(roleName).orElseThrow(()->new ResourceNotFoundException("Role Name Not Available : " + roleName));
        role.setName(tempRole);
        return roleRepository.save(role);
    }
    
}
