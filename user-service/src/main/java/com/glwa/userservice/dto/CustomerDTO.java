package com.glwa.userservice.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record CustomerDTO(
        Long id,
        String firstname,
        String name,
        String placeOfBirth,
        Date dateOfBirth,
        String nationality,
        String cin,
        String username,
        String email,
        String phone) {
}
