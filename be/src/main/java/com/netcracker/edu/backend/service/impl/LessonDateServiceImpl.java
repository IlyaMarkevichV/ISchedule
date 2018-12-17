package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.LessonDate;
import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.repository.LessonDateRepository;
import com.netcracker.edu.backend.resource.DateHelper;
import com.netcracker.edu.backend.service.LessonDateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class LessonDateServiceImpl implements LessonDateService {

    public static final Logger log = LoggerFactory.getLogger(LessonDateServiceImpl.class);

    private LessonDateRepository repository;

    @Autowired
    public LessonDateServiceImpl(LessonDateRepository repository) {
        this.repository = repository;
    }

    @Override
    public LessonDate save(LessonDate lessonDate) {
        return repository.save(lessonDate);
    }

    @Override
    public Iterable<Lesson> getProfessorLessonsBetweenMondayAndSaturday(Integer professorId, Date from) {
        Date[] dates = DateHelper.getMondayAndSaturday(from);
        return repository.getProfessorLessonsBetween(professorId, dates[0], dates[1]);
    }

    @Override
    public Iterable<Lesson> getGroupLessonsBetween(UniversityGroup group, Date from) {
        Date[] dates = DateHelper.getMondayAndSaturday(from);
        return repository.getGroupLessonsBetween(group, dates[0], dates[1]);
    }
}
