package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Day;
import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

public interface LessonRepository extends
        CrudRepository<Lesson, Integer>,
        PagingAndSortingRepository<Lesson, Integer> {

    @Query(value = "SELECT lesson FROM Lesson lesson where lesson.professor.id=?1 AND lesson.day.id=?2")
    Iterable<Lesson> getLessonsByProfessorIdAndDayId(Integer professorId, Integer dayId);

}
