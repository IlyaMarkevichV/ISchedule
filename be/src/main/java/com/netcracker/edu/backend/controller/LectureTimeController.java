package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.LectureTime;
import com.netcracker.edu.backend.service.LectureTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/lecture-time")
public class LectureTimeController {

    private LectureTimeService lectureTimeService;

    @Autowired
    public LectureTimeController(LectureTimeService lectureTimeService) {
        this.lectureTimeService = lectureTimeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LectureTime> getLectureTimeById(@RequestParam @PathVariable(name = "id") Integer id) {
        Optional<LectureTime> lectureTime = lectureTimeService.getLectureById(id);
        return lectureTime.isPresent() ? ResponseEntity.ok(lectureTime.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<LectureTime> getAllLectureTimes() {
        return lectureTimeService.getAllLectureTimes();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    LectureTime saveLectureTime(@RequestBody LectureTime lectureTime) {
        return lectureTimeService.saveLectureTime(lectureTime);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity deleteLectureTime(@RequestParam @PathVariable(name = "id") Integer id) {
        lectureTimeService.deleteLectureTime(id);
        return ResponseEntity.noContent().build();
    }
}
