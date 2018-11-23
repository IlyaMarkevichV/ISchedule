package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Student;
import com.netcracker.edu.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**/
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Student saveStudentProfessor(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable(name = "id") Integer id) {
        service.deleteStudents(id);
        return ResponseEntity.noContent().build();
    }
}
