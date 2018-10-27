package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.StudentEntityViewModel;
import com.netcracker.edu.fapi.service.StudentEntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba")
public class StudentEntityDataController {

    @Autowired
    private StudentEntityDataService studentEntityDataService;

    @RequestMapping
    public ResponseEntity<List<StudentEntityViewModel>> getAllStudentEntities() {
        return ResponseEntity.ok(studentEntityDataService.getAll());
    }

    // TODO: server validation
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentEntityViewModel> saveStudentEntity(@RequestBody StudentEntityViewModel studentEntityViewModel) {
        if (studentEntityViewModel != null) {
            return ResponseEntity.ok(studentEntityDataService.saveStudentEntity(studentEntityViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentEntity(@PathVariable Integer id) {
        studentEntityDataService.deleteStudentEntity(id);
    }
}
