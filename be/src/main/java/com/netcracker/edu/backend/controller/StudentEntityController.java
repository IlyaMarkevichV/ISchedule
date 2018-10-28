package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.StudentEntity;
import com.netcracker.edu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student-entities")
public class StudentEntityController {

    private StudentService studentService;

    @Autowired
    public StudentEntityController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentEntity> getStudentEntityById(@PathVariable(name = "id") Integer id) {
        Optional<StudentEntity> studentEntity = studentService.getStudentEntityById(id);
        if (studentEntity.isPresent()) {
            return ResponseEntity.ok(studentEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<StudentEntity> getAllStudentEntities(){
        return studentService.getAllStudentEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public StudentEntity saveStudentEntity(@RequestBody StudentEntity entity) {
        return studentService.saveStudentEntity(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudentEntity(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudentEntity(id);
        return ResponseEntity.noContent().build();
    }
}
