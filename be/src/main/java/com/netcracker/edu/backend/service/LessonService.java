package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Lesson;

import java.util.Optional;

public interface LessonService {
    Lesson saveLesson(Lesson lesson);

    Optional<Lesson> getLessonById(Integer id);

    Iterable<Lesson> getAllLessons();

    void deleteLesson(Integer id);
}
