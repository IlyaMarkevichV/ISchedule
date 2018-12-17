package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.LessonDate;
import com.netcracker.edu.backend.entity.UniversityGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;

public interface LessonDateRepository extends CrudRepository<LessonDate, Integer> {

    @Query("SELECT DISTINCT ld.lesson FROM LessonDate ld " +
            "INNER JOIN ld.lesson ls ON ld.lesson.id = ls.id " +
            "WHERE ls.professor.id = :professorId AND ld.date BETWEEN :dateFrom AND :dateTo " +
            "ORDER BY ld.lesson.lessonTime.begin ASC, ld.date ASC")
    Iterable<Lesson> getProfessorLessonsBetween(
            @Param("professorId") Integer professorId,
            @Param("dateFrom") Date from,
            @Param("dateTo") Date to);

    @Query(value = "SELECT DISTINCT ld.lesson FROM LessonDate ld " +
            "INNER JOIN ld.lesson.groups lessonGroups " +
            "where :uniGroup in (lessonGroups) AND ld.date BETWEEN :dateFrom AND :dateTo " +
            "ORDER BY ld.lesson.lessonTime.begin ASC, ld.date ASC")
    Iterable<Lesson> getGroupLessonsBetween(
            @Param("uniGroup") UniversityGroup group,
            @Param("dateFrom") Date from,
            @Param("dateTo") Date to);
}
