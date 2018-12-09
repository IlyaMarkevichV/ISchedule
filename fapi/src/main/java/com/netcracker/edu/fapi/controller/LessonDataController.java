package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.LessonViewModel;
import com.netcracker.edu.fapi.service.LessonDataService;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
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
    public ResponseEntity<LessonViewModel> getLessonById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(lessonDataService.getLessonById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<LessonViewModel> saveLesson(@RequestBody LessonViewModel lessonViewModel) {
        return lessonViewModel != null ? ResponseEntity.ok(lessonDataService.saveLesson(lessonViewModel)) : null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLesson(@PathVariable(name = "id") Integer id) {
        lessonDataService.deleteLesson(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<RestPageImpl<LessonViewModel>> getPage(HttpServletRequest request) {
        return ResponseEntity.ok(lessonDataService.getPage(request));
    }

    @RequestMapping(value = "/professor_schedule")
    public ResponseEntity<List<LessonViewModel>> getLessonsByProfessorIdAndDayId(
            @RequestParam(name = "professor_id") Integer professorId,
            @RequestParam(name = "date") Date from) {
        return ResponseEntity.ok(
                lessonDataService.getProfessorLessonsBetween(professorId, from)
        );
    }

    @RequestMapping(value = "/group_schedule")
    public ResponseEntity<List<LessonViewModel>> getGroupLessonsBetween(
            @RequestParam(name = "group_id") Integer groupId,
            @RequestParam(name = "date") Date from) {
        return ResponseEntity.ok(
                lessonDataService.getGroupLessonsBetween(groupId, from)
        );
    }
}