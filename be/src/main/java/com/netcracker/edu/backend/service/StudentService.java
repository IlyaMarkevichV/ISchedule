package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.StudentEntity;

import java.util.Optional;

public interface StudentService {
    StudentEntity saveStudentEntity(StudentEntity entity);

    Optional<StudentEntity> getStudentEntityById(Integer id);

    Iterable<StudentEntity> getAllStudentEntities();

    void deleteStudentEntity(Integer id);
}
