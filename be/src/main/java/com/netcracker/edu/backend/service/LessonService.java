package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Day;
import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LessonService {
    Lesson saveLesson(Lesson lesson);

    Optional<Lesson> getLessonById(Integer id);

    void deleteLesson(Integer id);

    Page<Lesson> getPage(Pageable pageable);

    Iterable<Lesson> getLessonsByProfessorIdAndDayId(Integer professorId, Integer dayId);
}
