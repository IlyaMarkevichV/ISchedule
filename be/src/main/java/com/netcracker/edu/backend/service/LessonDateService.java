package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.LessonDate;

import java.sql.Date;
import java.util.Optional;

public interface LessonDateService {
    LessonDate save(LessonDate lessonDate);

    /*Optional<Lesson> getProfessorLessons(Integer);*/
    Iterable<Lesson> getProfessorLessonsBetweenMondayAndSaturday(Integer professorId, Date from);
}
