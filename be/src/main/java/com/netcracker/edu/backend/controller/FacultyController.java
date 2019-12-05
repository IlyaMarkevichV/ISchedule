package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Faculty;
import com.netcracker.edu.backend.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**/
@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    private FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Faculty> getFacultyById(@PathVariable(name = "id") Integer id) {
        Optional<Faculty> faculty = facultyService.getFacultyById(id);
        return faculty.isPresent() ? ResponseEntity.ok(faculty.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Faculty> getFaculties() {
        return facultyService.getAllFaculties();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Faculty saveFaculty(@RequestBody Faculty faculty) {
        return facultyService.saveFaculty(faculty);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFaculty(@PathVariable Integer id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
