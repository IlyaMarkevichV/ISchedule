package backend.service;

import backend.entity.Lesson;
import backend.entity.LessonDate;
import backend.entity.UniversityGroup;

import java.sql.Date;
import java.util.Optional;

public interface LessonDateService {
    LessonDate save(LessonDate lessonDate);

    /*Optional<Lesson> getProfessorLessons(Integer);*/
    Iterable<Lesson> getProfessorLessonsBetweenMondayAndSaturday(Integer professorId, Date from);

    Iterable<Lesson> getGroupLessonsBetween(UniversityGroup group, Date from);
}
