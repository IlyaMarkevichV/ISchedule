package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.LessonInfo;
import com.netcracker.edu.backend.service.LessonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lesson-infos")
public class LessonInfoController {

    private LessonInfoService service;

    @Autowired
    public LessonInfoController(LessonInfoService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<LessonInfo> getAllLessonInfos() {
        return service.getAllLessonInfos();
    }
}
