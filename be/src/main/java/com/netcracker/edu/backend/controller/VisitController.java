package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Schedule;
import com.netcracker.edu.backend.entity.Student;
import com.netcracker.edu.backend.entity.Visit;
import com.netcracker.edu.backend.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/visit-entities")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Visit> getVisitById(@PathVariable(name = "id") Integer id) {
        Optional<Visit> visit = visitService.getVisitById(id);
        return visit.isPresent() ? ResponseEntity.ok(visit.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Visit saveVisit(@RequestBody Visit visit) {
        return visitService.saveVisit(visit);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteVisit(@PathVariable(name = "id") Integer id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }
}
