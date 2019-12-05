package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Attendance;
import com.netcracker.edu.backend.repository.AttendanceRepository;
import com.netcracker.edu.backend.resource.DateHelper;
import com.netcracker.edu.backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private AttendanceRepository repository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Attendance save(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public Iterable<Attendance> saveAll(Iterable<Attendance> attendances) {
        return repository.saveAll(attendances);
    }

    @Override
    public Optional<Attendance> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Attendance> getAttendancesByStatusAndStudentIdAndDateBetween(byte status, int studentId, Date from, Date to) {
        return repository.getAttendancesByStatusAndStudent_IdAndDateBetween(
                status, studentId, from, DateHelper.addNDays(to, 1));
    }

    @Override
    public Iterable<Attendance> getAttendancesByStatusAndGroupIdAndLessonIdDateBetween(byte status, int groupId, int lessonId, Date from, Date to) {
        return repository.getAttendancesByStatusAndStudent_Group_IdAndLesson_IdAndDateBetween(status, groupId, lessonId, from, to);
    }
}
