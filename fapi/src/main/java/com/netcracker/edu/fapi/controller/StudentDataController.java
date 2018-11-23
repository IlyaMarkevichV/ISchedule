package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.StudentViewModel;
import com.netcracker.edu.fapi.service.StudentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-students")
public class StudentDataController {

    @Autowired
    private StudentDataService studentDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<StudentViewModel>> getAllStudentEntities() {
        return ResponseEntity.ok(studentDataService.getAll());
    }

    // TODO: server validation
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<StudentViewModel> saveStudentEntity(@RequestBody StudentViewModel studentViewModel) {
        if (studentViewModel != null) {
            return ResponseEntity.ok(studentDataService.saveStudent(studentViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentEntity(@PathVariable String id) {
        studentDataService.deleteStudent(Integer.valueOf(id));
    }
}
