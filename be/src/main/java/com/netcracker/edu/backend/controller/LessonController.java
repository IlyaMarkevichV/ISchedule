package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lesson> getLessonById(@PathVariable(name = "id") Integer id) {
        Optional<Lesson> schedule = lessonService.getLessonById(id);
        return schedule.isPresent() ? ResponseEntity.ok(schedule.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Lesson saveLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLesson(@PathVariable(name = "id") Integer id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/pages")
    public List<Lesson> getPage(Pageable pageable) {
        System.out.println(pageable);
        return lessonService.getPage(pageable);
    }
}
