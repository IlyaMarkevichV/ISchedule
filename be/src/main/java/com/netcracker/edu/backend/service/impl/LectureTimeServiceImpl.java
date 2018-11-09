package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.LectureTime;
import com.netcracker.edu.backend.repository.LectureTimeRepository;
import com.netcracker.edu.backend.service.LectureTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LectureTimeServiceImpl implements LectureTimeService {

    private LectureTimeRepository repository;

    @Autowired
    public LectureTimeServiceImpl(LectureTimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public LectureTime saveLectureTime(LectureTime lectureTime) {
        return repository.save(lectureTime);
    }

    @Override
    public Optional<LectureTime> getLectureById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<LectureTime> getAllLectureTimes() {
        return repository.findAll();
    }

    @Override
    public void deleteLectureTime(Integer id) {
        repository.deleteById(id);
    }
}
