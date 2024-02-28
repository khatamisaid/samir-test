package com.dashboard.service;

import java.util.List;

import com.dashboard.entity.Role;
import com.dashboard.model.RoleDto;

public interface RoleService {
    List<Role> getAllRoles();
    Role addRole(RoleDto role);
    Role updateRole(String roleName, String tempRole);
}
