package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Faculty;

import java.util.Optional;

// TODO: necessary?
public interface FacultyService {
    Faculty saveFacultyService(Faculty entity);

    Optional<Faculty> getProfessorById(Integer id);

    Iterable<Faculty> getAllFacultyEntities();

    void deleteFacultyEntity(Integer id);
}
