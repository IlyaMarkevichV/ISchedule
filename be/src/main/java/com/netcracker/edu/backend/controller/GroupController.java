package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.service.UniversityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/group-entities")
public class GroupController {
    private UniversityGroupService universityGroupService;

    @Autowired
    public GroupController(UniversityGroupService universityGroupService) {
        this.universityGroupService = universityGroupService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UniversityGroup> getGroupEntityById(@PathVariable(name = "id") Integer id) {
        Optional<UniversityGroup> groupEntity = universityGroupService.getGroupEntityById(id);
        if (groupEntity.isPresent()) {
            return ResponseEntity.ok(groupEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UniversityGroup> getAllGroupEntities() {
        return universityGroupService.getAllGroupEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UniversityGroup saveGroupEntity(@RequestBody UniversityGroup entity) {
        return universityGroupService.saveGroupEntity(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroupEntity(@PathVariable(name = "id") Integer id) {
        universityGroupService.deleteGroupEntity(id);
        return ResponseEntity.noContent().build();
    }
}
