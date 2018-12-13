package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Attendance;
import com.netcracker.edu.backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private AttendanceService service;

    @Autowired
    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Attendance findById(@PathVariable(name = "id") Integer id) {
        Optional<Attendance> optional = service.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Attendance saveAttendance(@RequestBody Attendance attendance) {
        return service.save(attendance);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Iterable<Attendance> saveAll(@RequestBody Iterable<Attendance> attendances) {
        return service.saveAll(attendances);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Iterable<Attendance> getAttendancesByStatusAndStudentIdAndDateBetween(
            @RequestParam(name = "status") byte status,
            @RequestParam(name = "student_id") int studentId,
            @RequestParam(name = "from") Date from,
            @RequestParam(name = "to") Date to) {

        return service.getAttendancesByStatusAndStudentIdAndDateBetween(status, studentId, from, to);
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public Iterable<Attendance> getAttendancesByStatusAndGroupIdAndDateBetween(
            @RequestParam(name = "status") byte status,
            @RequestParam(name = "group_id") int groupId,
            @RequestParam(name = "from") Date from,
            @RequestParam(name = "to") Date to) {

        return service.getAttendancesByStatusAndGroupIdAndDateBetween(status, groupId, from, to);
    }
}
