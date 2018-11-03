package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Schedule;
import com.netcracker.edu.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/schedule-lectures")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Schedule> getLectureFromScheduleById(@PathVariable(name = "id") Integer id) {
        Optional<Schedule> schedule = scheduleService.getLectureFromScheduleById(id);
        return schedule.isPresent() ? ResponseEntity.ok(schedule.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Schedule> getAllLecturesFromSchedule() {
        return scheduleService.getAllLecturesFromSchedule();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Schedule saveLectureInSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveLectureInSchedule(schedule);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLectureFromSchedule(@PathVariable(name = "id") Integer id) {
        scheduleService.deleteLectureFromSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
