package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Faculty;
import com.netcracker.edu.backend.repository.FacultyRepository;
import com.netcracker.edu.backend.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository repository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteFaculty(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Faculty> getFacultyById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Faculty> getAllFaculties() {
        return repository.findAll();
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return repository.save(faculty);
    }
}
