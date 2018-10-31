package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Professor;

import java.util.Optional;

public interface ProfessorService {
    Professor saveProfessor(Professor entity);

    Optional<Professor> getProfessorById(Integer id);

    Iterable<Professor> getAllProfessors();

    void deleteProfessor(Integer id);
}
