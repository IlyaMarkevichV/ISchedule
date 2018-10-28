package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.ProfessorEntity;

import java.util.Optional;

public interface ProfessorService {
    ProfessorEntity saveProfessorEntity(ProfessorEntity entity);

    Optional<ProfessorEntity> getProfessorById(Integer id);

    Iterable<ProfessorEntity> getAllProfessorEntities();

    void deleteProfessorEntity(Integer id);
}
