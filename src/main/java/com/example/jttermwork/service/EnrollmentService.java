package com.example.jttermwork.service;

import com.example.jttermwork.dto.EnrollmentRequest;
import com.example.jttermwork.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment create(EnrollmentRequest request);
    List<Enrollment> getAll();
    Enrollment getById(Long id);
    Enrollment update(Long id, EnrollmentRequest request);
    void delete(Long id);
}
