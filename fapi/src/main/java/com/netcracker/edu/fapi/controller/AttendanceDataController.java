package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.AttendanceViewModel;
import com.netcracker.edu.fapi.service.AttendanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ba-attendance")
public class AttendanceDataController {

    private AttendanceDataService service;

    @Autowired
    public AttendanceDataController(AttendanceDataService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public AttendanceViewModel save(@RequestBody AttendanceViewModel attendance) {
        return service.save(attendance);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<AttendanceViewModel> saveAll(@RequestBody List<AttendanceViewModel> attendanceList) {
        return service.saveAll(attendanceList);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Iterable<AttendanceViewModel> getAttendancesByStatusAndStudentIdAndDateBetween(
            @RequestParam(name = "status") byte status,
            @RequestParam(name = "student_id") int studentId,
            @RequestParam(name = "lesson_id") int lessonId,
            @RequestParam(name = "from") Date from,
            @RequestParam(name = "to") Date to) {

        return service.getAttendancesByStatusAndStudentIdAndLessonIdAndDateBetween(status, studentId, lessonId, from, to);
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public Iterable<AttendanceViewModel> getAttendancesByStatusAndGroupIdAndDateBetween(
            @RequestParam(name = "status") byte status,
            @RequestParam(name = "group_id") int groupId,
            @RequestParam(name = "lesson_id") int lessonId,
            @RequestParam(name = "from") Date from,
            @RequestParam(name = "to") Date to) {

        return service.getAttendancesByStatusAndGroupIdAndLessonIdAndDateBetween(status, groupId, lessonId, from, to);
    }
}
