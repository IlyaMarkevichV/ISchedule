package backend.service;

import backend.entity.Attendance;

import java.sql.Date;
import java.util.Optional;

public interface AttendanceService {

    Iterable<Attendance> getAttendancesByStatusAndGroupIdAndLessonIdDateBetween(
            byte status, int groupId, int lessonId, Date from, Date to);

    Iterable<Attendance> getAttendancesByStatusAndStudentIdAndDateBetween(
            byte status, int studentId, Date from, Date to);

    Optional<Attendance> findById(Integer id);

    Attendance save(Attendance attendance);

    Iterable<Attendance> saveAll(Iterable<Attendance> attendances);
}
