# JT Term Work - Learning Management REST API

This mini project is a **Spring Boot REST API** for a simple Learning Management domain.

## Objective
Build a secure API where:
- Admin manages users
- Instructors manage courses
- Students enroll in courses

## Tech Stack
- Java 26
- Spring Boot 3
- Spring Web, Spring Data JPA
- Spring Security (HTTP Basic + role-based authorization)
- H2 in-memory database
- Lombok (for boilerplate reduction)

## Entities and Relationships
1. **User**
2. **Role**
3. **Course**
4. **Enrollment**

Key mappings:
- User ↔ Role: Many-to-Many
- Course → Instructor(User): Many-to-One
- Enrollment → Student(User): Many-to-One
- Enrollment → Course: Many-to-One

## Credentials (seeded at startup)
- `admin@jt.local / admin123` (ROLE_ADMIN)
- `instructor@jt.local / instructor123` (ROLE_INSTRUCTOR)
- `student@jt.local / student123` (ROLE_STUDENT)

## API Endpoints
- `/api/users` (ADMIN only)
- `/api/courses` (ADMIN, INSTRUCTOR)
- `/api/enrollments` (ADMIN, STUDENT)

Use Postman with **Basic Auth** to test CRUD operations.
