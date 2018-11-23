package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.entity.Professor;
import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.resource.Constants;
import com.netcracker.edu.backend.service.AccountService;
import com.netcracker.edu.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private ProfessorService service;

    @Autowired
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Professor saveProfessor(@RequestBody Professor professor) {
        return service.saveProfessor(professor);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Professor> getAllProfessors() {
        return service.getAllProfessors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProfessor(@PathVariable(name = "id") Integer id) {
        service.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
