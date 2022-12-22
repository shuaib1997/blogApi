package com.codewithshuaib.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long userId;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 characters !!")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    @Size(min=3,max =10,message = "Password must be between 3 to 10 char!!")
    private String password;
    @NotEmpty
    private String about;
}
