package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.FacultyViewModel;
import com.netcracker.edu.fapi.service.FacultyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-faculties")
public class FacultyDataController {

    private FacultyDataService facultyDataService;

    @Autowired
    public FacultyDataController(FacultyDataService facultyDataService) {
        this.facultyDataService = facultyDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FacultyViewModel> getFacultyById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(facultyDataService.getFacultyById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<FacultyViewModel>> getAllFaculties() {
        return ResponseEntity.ok(facultyDataService.getAllFaculties());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<FacultyViewModel> saveFaculty(@RequestBody FacultyViewModel facultyViewModel) {
        System.out.println(facultyViewModel);
        return facultyViewModel != null ? ResponseEntity.ok(facultyDataService.saveFaculty(facultyViewModel)) : null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFaculty(@PathVariable(name = "id") Integer id) {
        facultyDataService.deleteFaculty(id);
    }
}
