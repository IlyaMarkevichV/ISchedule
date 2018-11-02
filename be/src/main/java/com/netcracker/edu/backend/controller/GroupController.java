package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.service.UniversityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/group-entities")
public class GroupController {
    private UniversityGroupService universityGroupService;

    @Autowired
    public GroupController(UniversityGroupService universityGroupService) {
        this.universityGroupService = universityGroupService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UniversityGroup> getGroupById(@PathVariable(name = "id") Integer id) {
        Optional<UniversityGroup> groupEntity = universityGroupService.getGroupById(id);
        if (groupEntity.isPresent()) {
            return ResponseEntity.ok(groupEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UniversityGroup> getAllGroups() {
        return universityGroupService.getAllGroups();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UniversityGroup saveGroup(@RequestBody UniversityGroup entity) {
        return universityGroupService.saveGroup(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@PathVariable(name = "id") Integer id) {
        universityGroupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}