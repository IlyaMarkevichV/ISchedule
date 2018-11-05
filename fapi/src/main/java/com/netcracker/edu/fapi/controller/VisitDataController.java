package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.VisitViewModel;
import com.netcracker.edu.fapi.service.VisitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;

@RestController
@RequestMapping("/api/ba-visit")
public class VisitDataController {

    private VisitDataService visitDataService;

    @Autowired
    public VisitDataController(VisitDataService visitDataService) {
        this.visitDataService = visitDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<VisitViewModel> getVisitById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(visitDataService.getVisitById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<VisitViewModel>> getAllVisits() {
        return ResponseEntity.ok(visitDataService.getAllVisits());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<VisitViewModel> saveVisit(@RequestBody VisitViewModel visit) {
        return visit != null ? ResponseEntity.ok(visitDataService.saveVisit(visit)) : null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteVisit(@PathVariable(name = "id") Integer id) {
        visitDataService.deleteVisit(id);
    }

}
