package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.ProfessorEntity;
import com.netcracker.edu.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/professor-entities")
public class ProfessorEntityController {

    private ProfessorService professorService;

    @Autowired
    public ProfessorEntityController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfessorEntity> getProfessorEntityById(@PathVariable(name = "id") Integer id) {
        Optional<ProfessorEntity> professorEntity = professorService.getProfessorById(id);
        if (professorEntity.isPresent()) {
            return ResponseEntity.ok(professorEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<ProfessorEntity> getAllProfessorEntities() {
        return professorService.getAllProfessorEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProfessorEntity saveProfessorEntity(@RequestBody ProfessorEntity entity) {
        return professorService.saveProfessorEntity(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProfessorEntity(@PathVariable(name = "id") Integer id) {
        professorService.deleteProfessorEntity(id);
        return ResponseEntity.noContent().build();
    }
}
