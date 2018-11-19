package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.entity.StudentGroup;
import com.netcracker.edu.backend.service.AccountService;
import com.netcracker.edu.backend.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private AccountService service;
    private StudentGroupService studentGroupService;

    @Autowired
    public StudentController(AccountService service, StudentGroupService studentGroupService) {
        this.service = service;
        this.studentGroupService = studentGroupService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Account saveStudentProfessor(@RequestBody Account account) {
        //add in 'account' table and also in 'student_professor' table too
        Account student = service.saveAccount(account);
        if (student == null) {
            return null;
        }

        //add student in table to bind him to the group
        StudentGroup studentGroup = studentGroupService.saveStudentGroup(new StudentGroup(account));
        if (studentGroup == null) {
            service.deleteAccount(account.getAccountId());
            return null;
        }
        return student;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<StudentGroup> getAllStudents() {
        return studentGroupService.getAllStudentGroups();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable(name = "id") Integer id) {
        //delete from 'account' & 'student_professor' tables by one request
        System.out.println("step one");
        service.deleteAccount(id);

        //delete from 'student_group'
        studentGroupService.deleteStudentByAccountId(id);
        return ResponseEntity.noContent().build();
    }
}
