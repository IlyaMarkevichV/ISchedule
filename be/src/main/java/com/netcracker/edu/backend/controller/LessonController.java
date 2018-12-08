package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.service.LessonDateService;
import com.netcracker.edu.backend.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private LessonService lessonService;
    private LessonDateService lessonDateService;

    @Autowired
    public LessonController(LessonService lessonService, LessonDateService lessonDateService) {
        this.lessonService = lessonService;
        this.lessonDateService = lessonDateService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lesson> getLessonById(@PathVariable(name = "id") Integer id) {
        Optional<Lesson> schedule = lessonService.getLessonById(id);
        return schedule.isPresent() ? ResponseEntity.ok(schedule.get()) : null;
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Lesson saveLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLesson(@PathVariable(name = "id") Integer id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Lesson> getPage(Pageable pageable) {
        return lessonService.getPage(pageable);
    }

    @RequestMapping(value = "/professor_schedule")
    public Iterable<Lesson> getLessonsByProfessorIdAndDates(
            @RequestParam(name = "professor_id") Integer professorId,
            @RequestParam(name = "from") Date from) {

        return lessonDateService.getProfessorLessonsBetweenMondayAndSaturday(
                professorId,
                from);
    }
}
