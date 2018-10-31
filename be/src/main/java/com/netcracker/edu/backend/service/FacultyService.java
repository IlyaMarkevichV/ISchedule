package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Faculty;

import java.util.Optional;

// TODO: necessary?
public interface FacultyService {
    Faculty saveFaculty(Faculty entity);

    Optional<Faculty> getFacultyById(Integer id);

    Iterable<Faculty> getAllFaculty();

    void deleteFaculty(Integer id);
}
