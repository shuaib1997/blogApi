package com.codewithshuaib.blog.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
