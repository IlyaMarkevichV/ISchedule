package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.LessonViewModel;
import com.netcracker.edu.fapi.service.LessonDataService;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/ba-lessons")
public class LessonDataController {

    private LessonDataService lessonDataService;

    @Autowired
    public LessonDataController(LessonDataService lessonDataService) {
        this.lessonDataService = lessonDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LessonViewModel> getLessonById(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(lessonDataService.getLessonById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<LessonViewModel>> getAllLessons() {
        return ResponseEntity.ok(lessonDataService.getAllLessons());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<LessonViewModel> saveLesson(@RequestBody LessonViewModel lessonViewModel) {
        return lessonViewModel != null ? ResponseEntity.ok(lessonDataService.saveLesson(lessonViewModel)) : null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLesson(@PathVariable(name = "id") Integer id) {
        lessonDataService.deleteLesson(id);
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public ResponseEntity<RestPageImpl<LessonViewModel>> getPage(HttpServletRequest request) {
        return ResponseEntity.ok(lessonDataService.getPage(request));
    }
}
