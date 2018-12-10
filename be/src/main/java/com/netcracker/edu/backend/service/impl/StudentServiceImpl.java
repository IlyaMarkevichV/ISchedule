package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Student;
import com.netcracker.edu.backend.repository.StudentRepository;
import com.netcracker.edu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student saveStudent(Student entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Student> getStudentByLogin(String login) {
        return repository.getStudentByAccount_Login(login);
    }

    @Override
    public void deleteStudents(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Student> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
