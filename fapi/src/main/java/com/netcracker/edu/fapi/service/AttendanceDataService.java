package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.AttendanceViewModel;

import java.sql.Date;
import java.util.List;

public interface AttendanceDataService {

    AttendanceViewModel save(AttendanceViewModel attendance);

    List<AttendanceViewModel> saveAll(List<AttendanceViewModel> attendances);

    List<AttendanceViewModel> getAttendancesByStatusAndStudentIdAndDateBetween(byte status, int studentId, Date from, Date to);

    List<AttendanceViewModel> getAttendancesByStatusAndGroupIdAndDateBetween(byte status, int groupId, Date from, Date to);
}
