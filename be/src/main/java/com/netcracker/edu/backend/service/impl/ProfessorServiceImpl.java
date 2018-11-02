package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Professor;
import com.netcracker.edu.backend.repository.ProfessorRepository;
import com.netcracker.edu.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository repository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Professor saveProfessor(Professor entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Professor> getProfessorById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Professor> getAllProfessors() {
        return repository.findAll();
    }

    @Override
    public void deleteProfessor(Integer id) {
        repository.deleteById(id);
    }
}
