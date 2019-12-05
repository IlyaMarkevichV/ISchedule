package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {

    Iterable<Attendance> getAttendancesByStatusAndStudent_IdAndDateBetween(
            byte status, int studentId, Date from, Date to);

    Iterable<Attendance> getAttendancesByStatusAndStudent_Group_IdAndLesson_IdAndDateBetween(
            byte status, int studentId, int lessonId, Date from, Date to);
}
