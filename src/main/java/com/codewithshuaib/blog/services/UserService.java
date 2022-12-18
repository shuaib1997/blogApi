package com.codewithshuaib.blog.services;


import com.codewithshuaib.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,String userId);
    UserDto getUserById(String userId);
    List<UserDto> getAllUsers();
    void deleteUser(String userId);

}
