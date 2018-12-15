package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Attendance;

import java.sql.Date;
import java.util.Optional;

public interface AttendanceService {

    Iterable<Attendance> getAttendancesByStatusAndGroupIdAndLessonIdDateBetween(
            byte status, int groupId, int lessonId, Date from, Date to);

    Iterable<Attendance> getAttendancesByStatusAndStudentIdAndLessonIdDateBetween(
            byte status, int studentId, int lessonId, Date from, Date to);

    Optional<Attendance> findById(Integer id);

    Attendance save(Attendance attendance);

    Iterable<Attendance> saveAll(Iterable<Attendance> attendances);
}
