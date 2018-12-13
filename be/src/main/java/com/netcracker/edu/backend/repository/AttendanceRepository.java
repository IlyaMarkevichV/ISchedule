package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {

    Iterable<Attendance> getAttendancesByStatusAndStudent_IdAndDateBetween(
            byte status, int studentId, Date from, Date to);

    Iterable<Attendance> getAttendancesByStatusAndStudent_Group_IdAndDateBetween(
            byte status, int studentId, Date from, Date to);
}
