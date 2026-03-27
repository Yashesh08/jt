package com.example.jttermwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequest {
    @NotNull
    private Long studentId;
    @NotNull
    private Long courseId;
    @NotBlank
    private String status;
}
