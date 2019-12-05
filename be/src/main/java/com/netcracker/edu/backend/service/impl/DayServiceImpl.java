package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Day;
import com.netcracker.edu.backend.repository.DayRepository;
import com.netcracker.edu.backend.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayServiceImpl implements DayService {

    private DayRepository repository;

    @Autowired
    public DayServiceImpl(DayRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Day> getAllDays() {
        return repository.findAll();
    }
}
