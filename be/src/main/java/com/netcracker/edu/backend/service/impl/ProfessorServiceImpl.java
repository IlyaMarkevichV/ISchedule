package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.ProfessorEntity;
import com.netcracker.edu.backend.repository.ProfessorRepository;
import com.netcracker.edu.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository repository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessorEntity saveProfessorEntity(ProfessorEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<ProfessorEntity> getProfessorById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<ProfessorEntity> getAllProfessorEntities() {
        return repository.findAll();
    }

    @Override
    public void deleteProfessorEntity(Integer id) {
        repository.deleteById(id);
    }
}
