package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.LessonTime;
import com.netcracker.edu.backend.repository.LessonTimeRepository;
import com.netcracker.edu.backend.service.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {

    private LessonTimeRepository repository;

    @Autowired
    public LessonTimeServiceImpl(LessonTimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<LessonTime> getAllLessonTimes() {
        return repository.findAll();
    }
}
