package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.LessonDateRepository;
import com.netcracker.edu.backend.repository.LessonRepository;
import com.netcracker.edu.backend.schedule.LessonScheduler;
import com.netcracker.edu.backend.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private static final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);

    private LessonRepository repository;
    private LessonDateRepository lessonDateRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository repository, LessonDateRepository lessonDateRepository) {
        this.repository = repository;
        this.lessonDateRepository = lessonDateRepository;
    }

    @Override
    public Lesson saveLesson(Lesson lesson) {
        if (lesson.getId() == 0) {
            lesson = repository.save(lesson);

            LocalDate localDate = LocalDate
                    .now()
                    .with(DayOfWeek.values()[lesson.getDay().getId()-1]);

            lessonDateRepository.save(new LessonDate(lesson, Date.valueOf(localDate)));
            return lesson;
        } else {
            return repository.save(lesson);
        }
    }

    @Override
    public Optional<Lesson> getLessonById(Integer id) {
        return repository.findById(id);
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

    @Override
    public Iterable<Lesson> getLessonsByProfessorIdAndDayId(Integer professorId, Integer dayId) {
        return repository.getLessonsByProfessorIdAndDayId(professorId, dayId);
    }
}
