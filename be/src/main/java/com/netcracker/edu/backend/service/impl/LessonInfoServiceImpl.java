package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.LessonInfo;
import com.netcracker.edu.backend.repository.LessonInfoRepository;
import com.netcracker.edu.backend.service.LessonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonInfoServiceImpl implements LessonInfoService {

    private LessonInfoRepository repository;

    @Autowired
    public LessonInfoServiceImpl(LessonInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<LessonInfo> getAllLessonInfos() {
        return repository.findAll();
    }
}
