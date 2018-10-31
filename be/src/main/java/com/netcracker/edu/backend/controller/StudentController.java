package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Student;
import com.netcracker.edu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student-entities")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentEntityById(@PathVariable(name = "id") Integer id) {
        Optional<Student> studentEntity = studentService.getStudentById(id);
        if (studentEntity.isPresent()) {
            return ResponseEntity.ok(studentEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Student> getAllStudentEntities(){
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student saveStudentEntity(@RequestBody Student entity) {
        return studentService.saveStudent(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudentEntity(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudents(id);
        return ResponseEntity.noContent().build();
    }
}
