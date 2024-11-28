package com.glwa.auth.dto;

import com.glwa.auth.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
    private CustomerDTO customerDTO;
}
