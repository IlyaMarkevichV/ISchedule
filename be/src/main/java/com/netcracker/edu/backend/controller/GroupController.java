package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.service.UniversityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**/
@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private UniversityGroupService service;

    @Autowired
    public GroupController(UniversityGroupService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UniversityGroup> getGroupById(@PathVariable(name = "id") Integer id) {
        Optional<UniversityGroup> groupEntity = service.getGroupById(id);
        if (groupEntity.isPresent()) {
            return ResponseEntity.ok(groupEntity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UniversityGroup> getAllGroups() {
        return service.getAllGroups();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UniversityGroup saveGroup(@RequestBody UniversityGroup entity) {
        return service.saveGroup(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@PathVariable(name = "id") Integer id) {
        service.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public Page<UniversityGroup> getPage(Pageable pageable) {
        return service.getPage(pageable);
    }
}
