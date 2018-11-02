package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Subject;
import com.netcracker.edu.backend.repository.SubjectRepository;
import com.netcracker.edu.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository repository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return repository.save(subject);
    }

    @Override
    public Optional<Subject> getSubjectById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Subject> getAllSubjects() {
        return repository.findAll();
    }

    @Override
    public void deleteSubject(Integer id) {
        repository.deleteById(id);
    }
}
