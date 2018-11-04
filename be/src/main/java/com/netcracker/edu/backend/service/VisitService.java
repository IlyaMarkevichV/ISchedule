package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Visit;

import java.util.Optional;

public interface VisitService {
    Visit saveVisit(Visit visit);

    Optional<Visit> getVisitById(Integer id);

    Iterable<Visit> getAllVisits();

    void deleteVisit(Integer id);
}
