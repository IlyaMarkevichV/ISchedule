package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.StudentProfessor;
import com.netcracker.edu.backend.service.StudentProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class StudentProfessorController {

    private StudentProfessorService service;

    @Autowired
    public StudentProfessorController(StudentProfessorService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public StudentProfessor saveStudentProfessor(StudentProfessor studentProfessor) {
        return service.saveStudentProfessor(studentProfessor);
    }
}
