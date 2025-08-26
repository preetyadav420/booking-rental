package com.preet.bookingrental.Dtos;

import com.preet.bookingrental.Entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
