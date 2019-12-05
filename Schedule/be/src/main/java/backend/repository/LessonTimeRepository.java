package backend.repository;

import backend.entity.LessonTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTimeRepository extends CrudRepository<LessonTime, Integer> {
}
