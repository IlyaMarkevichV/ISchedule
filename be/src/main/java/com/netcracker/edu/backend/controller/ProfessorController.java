package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Professor;
import com.netcracker.edu.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/professor-entities")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Professor> getProfessorById(@PathVariable(name = "id") Integer id) {
        Optional<Professor> professorEntity = professorService.getProfessorById(id);
        if (professorEntity.isPresent()) {
            return ResponseEntity.ok(professorEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Professor> getAllProfessor() {
        return professorService.getAllProfessors();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Professor saveProfessor(@RequestBody Professor entity) {
        System.out.println(entity);
        return professorService.saveProfessor(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProfessor(@PathVariable(name = "id") Integer id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
