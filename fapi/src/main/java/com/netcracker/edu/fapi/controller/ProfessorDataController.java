package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.ProfessorViewModel;
import com.netcracker.edu.fapi.service.ProfessorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-professors")
public class ProfessorDataController {

    @Autowired
    private ProfessorDataService professorDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorViewModel>> getAllProfessors() {
        return ResponseEntity.ok(professorDataService.getAll());
    }

    // TODO: server validation
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProfessorViewModel> saveProfessor(@RequestBody ProfessorViewModel professorViewModel) {
        if (professorViewModel != null) {
            return ResponseEntity.ok(professorDataService.saveProfessor(professorViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProfessorEntity(@PathVariable String id) {
        professorDataService.deleteProfessor(Integer.valueOf(id));
    }
}
