package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.StudentProfessor;
import com.netcracker.edu.backend.repository.StudentProfessorRepository;
import com.netcracker.edu.backend.service.StudentProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfessorServiceImpl implements StudentProfessorService {

    private StudentProfessorRepository repository;

    @Autowired
    public StudentProfessorServiceImpl(StudentProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentProfessor saveStudentProfessor(StudentProfessor studentProfessor) {
        return repository.save(studentProfessor);
    }

    @Override
    public void deleteStudentProfessor(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<StudentProfessor> getStudentProfessors() {
        return repository.findAll();
    }

    @Override
    public List<StudentProfessor> getAllStudents() {
        return repository.getStudentProfessorsByAccount_Role("student");
    }

    @Override
    public List<StudentProfessor> getAllProfessors() {
        return repository.getStudentProfessorsByAccount_Role("professor");
    }
}
