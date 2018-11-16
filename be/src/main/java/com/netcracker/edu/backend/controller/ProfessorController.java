package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private AccountService service;

    @Autowired
    public ProfessorController(AccountService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Account saveProfessor(@RequestBody Account account) {
        return service.saveAccount(account);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Account> getAllProfessors() {
        return service.getAccountsByRole("professor");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProfessor(@PathVariable(name = "id") Integer id) {
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
