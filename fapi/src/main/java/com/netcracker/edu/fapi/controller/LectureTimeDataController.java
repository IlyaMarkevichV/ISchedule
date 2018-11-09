package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.LectureTimeViewModel;
import com.netcracker.edu.fapi.service.LectureTimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-lecture-time")
public class LectureTimeDataController {

    private LectureTimeDataService lectureTimeDataService;

    @Autowired
    public LectureTimeDataController(LectureTimeDataService lectureTimeDataService) {
        this.lectureTimeDataService = lectureTimeDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LectureTimeViewModel> getLectureTimeById(@RequestParam @PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(lectureTimeDataService.getLectureTimeById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<LectureTimeViewModel>> getAllLectureTimes() {
        return ResponseEntity.ok(lectureTimeDataService.getAllLectureTimes());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LectureTimeViewModel> saveLectureTime(@RequestBody LectureTimeViewModel lectureTime) {
        return ResponseEntity.ok(lectureTimeDataService.saveLectureTime(lectureTime));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLectureTime(@RequestParam @PathVariable(name = "id") Integer id) {
        lectureTimeDataService.deleteLectureTime(id);
    }
}
