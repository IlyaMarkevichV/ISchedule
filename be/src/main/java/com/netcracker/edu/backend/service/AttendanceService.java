package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Attendance;

import java.sql.Date;
import java.util.Optional;

public interface AttendanceService {

    Iterable<Attendance> getAttendancesByStatusAndGroupIdAndDateBetween(
            byte status, int groupId, Date from, Date to);

    Iterable<Attendance> getAttendancesByStatusAndStudentIdAndDateBetween(
            byte status, int studentId, Date from, Date to);

    Optional<Attendance> findById(Integer id);

    Attendance save(Attendance attendance);

    Iterable<Attendance> saveAll(Iterable<Attendance> attendances);
}
