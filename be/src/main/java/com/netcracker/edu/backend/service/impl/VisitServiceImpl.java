package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Visit;
import com.netcracker.edu.backend.repository.VisitRepository;
import com.netcracker.edu.backend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    private VisitRepository repository;

    @Autowired
    public VisitServiceImpl(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Visit saveVisit(Visit visit) {
        return repository.save(visit);
    }

    @Override
    public Optional<Visit> getVisitById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Visit> getAllVisits() {
        return repository.findAll();
    }

    @Override
    public void deleteVisit(Integer id) {
        repository.deleteById(id);
    }
}
