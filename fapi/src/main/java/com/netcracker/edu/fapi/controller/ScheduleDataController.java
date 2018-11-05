package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.ScheduleViewModel;
import com.netcracker.edu.fapi.service.ScheduleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-schedule-lectures")
public class ScheduleDataController {

    private ScheduleDataService scheduleDataService;

    @Autowired
    public ScheduleDataController(ScheduleDataService scheduleDataService) {
        this.scheduleDataService = scheduleDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ScheduleViewModel> getLectureFromScheduleById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(scheduleDataService.getLectureFromScheduleById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleViewModel>> getAllLecturesFromSchedule() {
        return ResponseEntity.ok(scheduleDataService.getAllLecturesFromSchedule());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ScheduleViewModel> saveLectureInSchedule(@RequestBody ScheduleViewModel scheduleViewModel) {
        return scheduleViewModel != null ? ResponseEntity.ok(scheduleDataService.saveLectureInSchedule(scheduleViewModel)) : null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLectureFromSchedule(@PathVariable(name = "id") Integer id) {
        scheduleDataService.deleteLectureFormSchedule(id);
    }
}
