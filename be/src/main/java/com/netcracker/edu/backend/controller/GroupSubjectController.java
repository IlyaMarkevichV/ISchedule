package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.GroupSubject;
import com.netcracker.edu.backend.service.GroupSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/group-subject-entities")
public class GroupSubjectController {

    private GroupSubjectService groupSubjectService;

    @Autowired
    public GroupSubjectController(GroupSubjectService groupSubjectService) {
        this.groupSubjectService = groupSubjectService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupSubject> getGroupSubjectById(@PathVariable(name = "id") Integer id) {
        Optional<GroupSubject> subject = groupSubjectService.getGroupSubjectById(id);
        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        }
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<GroupSubject> getAllGroupSubjects() {
        return groupSubjectService.getAllGroupSubjects();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public GroupSubject saveGroupSubject(@RequestBody GroupSubject subject) {
        return groupSubjectService.saveGroupSubject(subject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroupSubject(@PathVariable(name = "id") Integer id) {
        groupSubjectService.deleteGroupSubject(id);
        return ResponseEntity.noContent().build();
    }
}
