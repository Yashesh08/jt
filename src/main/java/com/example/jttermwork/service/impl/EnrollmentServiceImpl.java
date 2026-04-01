package com.example.jttermwork.service.impl;

import com.example.jttermwork.dto.EnrollmentRequest;
import com.example.jttermwork.entity.Course;
import com.example.jttermwork.entity.Enrollment;
import com.example.jttermwork.entity.User;
import com.example.jttermwork.repository.CourseRepository;
import com.example.jttermwork.repository.EnrollmentRepository;
import com.example.jttermwork.repository.UserRepository;
import com.example.jttermwork.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public Enrollment create(EnrollmentRequest request) {
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status(request.getStatus())
                .build();

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
    }

    @Override
    public Enrollment update(Long id, EnrollmentRequest request) {
        Enrollment enrollment = getById(id);
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(request.getStatus());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
