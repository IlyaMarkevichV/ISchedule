package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.LessonInfoViewModel;
import com.netcracker.edu.fapi.service.LessonInfoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ba-lesson-infos")
public class LessonInfoController {

    private LessonInfoDataService service;

    @Autowired
    public LessonInfoController(LessonInfoDataService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<LessonInfoViewModel>> getAllLessonInfos() {
        return ResponseEntity.ok(service.getAllLessonInfos());
    }
}
