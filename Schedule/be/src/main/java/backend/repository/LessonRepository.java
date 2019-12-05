package backend.repository;

import backend.entity.*;
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
            "WHERE lesson.professor = :professor AND " +
            "lesson.lessonTime = :lessonTime AND lesson.day = :day")
    long countOfSimilarLessonsForProfessor(@Param("professor") Professor professor,
                                           @Param("lessonTime") LessonTime lessonTime,
                                           @Param("day") Day day);

    @Query(value = "SELECT COUNT(lesson) " +
            "FROM Lesson lesson " +
            "WHERE (SELECT COUNT(g) FROM lesson.groups g WHERE g = :gr) > 0 AND " +
            "lesson.lessonTime = :lessonTime AND lesson.day = :day")
    long countOfSimilarLessonsForGroup(@Param("gr") UniversityGroup group,
                                       @Param("lessonTime") LessonTime lessonTime,
                                       @Param("day") Day day);
}
