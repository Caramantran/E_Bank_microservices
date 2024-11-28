package com.glwa.auth.request;

import com.glwa.auth.dto.CustomerDTO;
import com.glwa.auth.enums.Role;
import lombok.Getter;

@Getter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
    private CustomerDTO customerDTO;
}
