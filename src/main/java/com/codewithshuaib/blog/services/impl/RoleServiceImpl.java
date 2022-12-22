package com.codewithshuaib.blog.services.impl;

import com.codewithshuaib.blog.entities.Role;
import com.codewithshuaib.blog.entities.User;
import com.codewithshuaib.blog.repositories.RoleRepository;
import com.codewithshuaib.blog.repositories.UserRepository;
import com.codewithshuaib.blog.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {} to the database",role.getName());
        return this.roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}",roleName,email);
        User user=this.userRepository.findByEmail(email);
        Role role=this.roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles=this.roleRepository.findAll();
        return roles;
    }
}
