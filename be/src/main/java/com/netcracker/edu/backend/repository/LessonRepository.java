package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends
        CrudRepository<Lesson, Integer>,
        PagingAndSortingRepository<Lesson, Integer> {

    @Query(value = "SELECT lesson FROM Lesson lesson where lesson.professor.id=?1 AND lesson.day.id=?2")
    Iterable<Lesson> getLessonsByProfessorIdAndDayId(Integer professorId, Integer dayId);

    @Query(value = "SELECT COUNT(lesson) " +
            "FROM Lesson lesson " +
            "WHERE lesson.lessonInfo = :lessonInfo AND lesson.professor = :professor AND " +
            "lesson.lessonTime = :lessonTime AND lesson.day = :day")
    long countOfSimilarLessons(@Param("lessonInfo") LessonInfo lessonInfo,
                               @Param("professor") Professor professor,
                               @Param("lessonTime") LessonTime lessonTime,
                               @Param("day") Day day);
}
