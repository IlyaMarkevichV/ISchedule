package fapi.service;

import fapi.models.AttendanceViewModel;

import java.sql.Date;
import java.util.List;

public interface AttendanceDataService {

    AttendanceViewModel save(AttendanceViewModel attendance);

    List<AttendanceViewModel> saveAll(List<AttendanceViewModel> attendances);

    List<AttendanceViewModel> getAttendancesByStatusAndStudentIdAndDateBetween(byte status, int studentId, Date from, Date to);

    List<AttendanceViewModel> getAttendancesByStatusAndGroupIdAndLessonIdAndDateBetween(byte status, int groupId, int lessonId, Date from, Date to);
}
