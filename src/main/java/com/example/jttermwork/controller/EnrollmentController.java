package com.example.jttermwork.controller;

import com.example.jttermwork.dto.EnrollmentRequest;
import com.example.jttermwork.entity.Enrollment;
import com.example.jttermwork.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enrollment create(@Valid @RequestBody EnrollmentRequest request) {
        return enrollmentService.create(request);
    }

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAll();
    }

    @GetMapping("/{id}")
    public Enrollment getById(@PathVariable Long id) {
        return enrollmentService.getById(id);
    }

    @PutMapping("/{id}")
    public Enrollment update(@PathVariable Long id, @Valid @RequestBody EnrollmentRequest request) {
        return enrollmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enrollmentService.delete(id);
    }
}
