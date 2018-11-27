package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.LessonTime;
import com.netcracker.edu.backend.service.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lesson-times")
public class LessonTimeController {

    private LessonTimeService service;

    @Autowired
    public LessonTimeController(LessonTimeService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<LessonTime> getAllLessonTimes() {
        return service.getAllLessonTimes();
    }
}
