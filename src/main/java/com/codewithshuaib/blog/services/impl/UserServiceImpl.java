package com.codewithshuaib.blog.services.impl;

import com.codewithshuaib.blog.entities.User;
import com.codewithshuaib.blog.exceptions.ResourceNotFoundException;
import com.codewithshuaib.blog.payloads.UserDto;
import com.codewithshuaib.blog.repositories.UserRepository;
import com.codewithshuaib.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("adding user {} to the database",userDto.getName());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        log.info("updating user {} to the database",userDto.getName());
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id",userId+""));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        User updatedUser=this.userRepository.save(user);
        UserDto userDto1=this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id",userId+""));
        log.info("Fetching user {} from the database",user.getName());
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Fetching users from the database");
        List<User> users=this.userRepository.findAll();
       List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id",userId +""));
        log.info("Deleting user {} from the database",user.getName());
        this.userRepository.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

}
