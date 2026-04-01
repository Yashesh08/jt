package com.example.jttermwork.service;

import com.example.jttermwork.dto.CourseRequest;
import com.example.jttermwork.entity.Course;

import java.util.List;

public interface CourseService {
    Course create(CourseRequest request);
    List<Course> getAll();
    Course getById(Long id);
    Course update(Long id, CourseRequest request);
    void delete(Long id);
}
