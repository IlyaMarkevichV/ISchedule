package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Student;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student entity);

    Optional<Student> getStudentById(Integer id);

    void deleteStudents(Integer id);

    Page<Student> getPage(Pageable pageable);
}
