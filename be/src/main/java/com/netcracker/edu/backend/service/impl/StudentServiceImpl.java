package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.StudentEntity;
import com.netcracker.edu.backend.repository.StudentRepository;
import com.netcracker.edu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentServiceImpl implements StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentEntity saveStudentEntity(StudentEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<StudentEntity> getStudentEntityById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<StudentEntity> getAllStudentEntities() {
        return repository.findAll();
    }

    @Override
    public void deleteStudentEntity(Integer id) {
        repository.deleteById(id);
    }
}
