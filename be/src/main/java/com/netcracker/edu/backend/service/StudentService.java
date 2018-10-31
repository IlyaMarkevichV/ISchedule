package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Student;

import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student entity);

    Optional<Student> getStudentById(Integer id);

    Iterable<Student> getAllStudents();

    void deleteStudents(Integer id);
}
