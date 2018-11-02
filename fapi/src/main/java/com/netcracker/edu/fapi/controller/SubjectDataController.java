package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.SubjectViewModel;
import com.netcracker.edu.fapi.service.SubjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-subject")
public class SubjectDataController {

    @Autowired
    private SubjectDataService subjectDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SubjectViewModel>> getAllStudents() {
        return ResponseEntity.ok(subjectDataService.getAllSubjects());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubjectViewModel> getSubjectById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(subjectDataService.getSubjectById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SubjectViewModel> saveSubject(@RequestBody SubjectViewModel subject) {
        if (subject != null) {
            return ResponseEntity.ok(subjectDataService.saveSubject(subject));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSubject(@PathVariable(value = "id") Integer id) {
        subjectDataService.deleteSubject(id);
    }
}
