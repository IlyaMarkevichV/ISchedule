package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.entity.StudentGroup;
import com.netcracker.edu.backend.service.AccountService;
import com.netcracker.edu.backend.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        Account student =  service.saveAccount(account);
        if (student == null) {
            System.out.println(student);
            return student;
        }

        System.out.println(student);

        StudentGroup studentGroup =
                studentGroupService.saveStudent(new StudentGroup(account));
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
            service.deleteAccount(id);
            studentGroupService.deleteStudentByAccountId(id);
            return ResponseEntity.noContent().build();
    }
}
