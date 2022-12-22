package com.codewithshuaib.blog.controllers;

import com.codewithshuaib.blog.entities.Role;
import com.codewithshuaib.blog.payloads.RoleToUserForm;
import com.codewithshuaib.blog.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/roles/")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }
    @PostMapping("/addtouser")
    public ResponseEntity<String> addRoleToUser(@RequestBody RoleToUserForm form){
         roleService.addRoleToUser(form.getUsername(),form.getRoleName());
        return new ResponseEntity<>("Assigned", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Role>> getRole(){
        List<Role> roles=roleService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
