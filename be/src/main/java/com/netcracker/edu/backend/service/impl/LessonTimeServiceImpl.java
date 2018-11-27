package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.LessonTime;
import com.netcracker.edu.backend.repository.LessonTimeRepository;
import com.netcracker.edu.backend.service.LessonTimeService;
import org.springframework.stereotype.Service;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {

    private LessonTimeRepository repository;

    @Override
    public Iterable<LessonTime> getAllLessonTimes() {
        return repository.findAll();
    }
}
