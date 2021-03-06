package backend.service;

import backend.entity.Lesson;
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
