package com.example.jttermwork.repository;

import com.example.jttermwork.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
