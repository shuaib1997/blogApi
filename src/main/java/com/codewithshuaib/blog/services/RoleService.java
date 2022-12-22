package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.entities.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    void addRoleToUser(String email,String roleName);
    List<Role> getRoles();
}
