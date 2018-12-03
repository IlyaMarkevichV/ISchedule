package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProfessorService {
    Professor saveProfessor(Professor entity);

    Optional<Professor> getProfessorById(Integer id);

    void deleteProfessor(Integer id);

    Page<Professor> getPage(Pageable pageable);
}
