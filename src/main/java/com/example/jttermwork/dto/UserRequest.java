package com.example.jttermwork.dto;

import com.example.jttermwork.entity.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {
    @NotBlank
    private String fullName;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<RoleName> roles;
}
