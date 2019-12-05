package fapi.controller;

import fapi.models.LessonTimeViewModel;
import fapi.service.LessonTimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ba-lesson-times")
public class LessonTimeDataController {

    private LessonTimeDataService service;

    @Autowired
    public LessonTimeDataController(LessonTimeDataService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<LessonTimeViewModel>> getAllLessonTimes() {
        return ResponseEntity.ok(service.getAllLessonTimes());
    }
}
