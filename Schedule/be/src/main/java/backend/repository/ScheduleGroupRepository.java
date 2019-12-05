package backend.repository;

import backend.entity.Lesson;
import backend.entity.ScheduleGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleGroupRepository extends CrudRepository<ScheduleGroup, Integer> {

    @Query(value = "SELECT sg.lesson FROM ScheduleGroup sg " +
            "INNER JOIN sg.lesson l " +
            "WHERE l.day.dayName = :dayName AND sg.group.id = :groupId")
    Iterable<Lesson> getLessonsByGroupIdAndDayOfWeek(
            @Param("dayName") String day,
            @Param("groupId") Integer groupId);
}
