package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Professor;
import com.netcracker.edu.backend.repository.ProfessorRepository;
import com.netcracker.edu.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void deleteProfessor(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Professor> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
