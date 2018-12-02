package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.repository.LessonRepository;
import com.netcracker.edu.backend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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
        Optional<Lesson> lessonOptional = repository.findById(id);
        if (!lessonOptional.isPresent())
            return;

        Lesson lesson = lessonOptional.get();
        lesson.getGroups().clear();

        repository.save(lesson);
        repository.delete(lesson);
    }

    @Override
    public Page<Lesson> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
