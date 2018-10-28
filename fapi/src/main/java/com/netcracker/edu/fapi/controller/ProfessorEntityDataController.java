package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.ProfessorEntityViewModel;
import com.netcracker.edu.fapi.service.ProfessorEntityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-professor")
public class ProfessorEntityDataController {

    @Autowired
    private ProfessorEntityDataService professorEntityDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorEntityViewModel>> getAllProfessorEntities() {
        return ResponseEntity.ok(professorEntityDataService.getAll());
    }

    // TODO: server validation
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProfessorEntityViewModel> saveProfessorEntity(@RequestBody ProfessorEntityViewModel professorEntityViewModel) {
        if (professorEntityViewModel != null) {
            return ResponseEntity.ok(professorEntityDataService.saveProfessorEntity(professorEntityViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProfessorEntity(@PathVariable String id) {
        professorEntityDataService.deleteProfessorEntity(Integer.valueOf(id));
    }
}
