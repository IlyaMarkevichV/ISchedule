package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.repository.LessonRepository;
import com.netcracker.edu.backend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private LessonRepository repository;

    @Autowired
    public LessonServiceImpl(LessonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lesson saveLesson(Lesson lesson) {
        return repository.save(lesson);
    }

    @Override
    public Optional<Lesson> getLessonById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Lesson> getAllLessons() {
        return repository.findAll();
    }

    @Override
    public void deleteLesson(Integer id) {
        repository.deleteById(id);
    }
}
