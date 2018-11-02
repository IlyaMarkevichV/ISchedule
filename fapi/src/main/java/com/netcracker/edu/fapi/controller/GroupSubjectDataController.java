package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.GroupSubjectViewModel;
import com.netcracker.edu.fapi.service.GroupSubjectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ba-group-subject")
public class GroupSubjectDataController {

    @Autowired
    private GroupSubjectDataService groupSubjectDataService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<GroupSubjectViewModel>> getAllGroupSubjects() {
        return ResponseEntity.ok(groupSubjectDataService.getAllGroupSubjects());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupSubjectViewModel> getGroupSubjectById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(groupSubjectDataService.getGroupSubjectById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public GroupSubjectViewModel saveGroupSubject(GroupSubjectViewModel subjectViewModel) {
        if (subjectViewModel != null) {
            return groupSubjectDataService.saveGroupSubject(subjectViewModel);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGroupSubject(@PathVariable(name = "id") Integer id) {
        groupSubjectDataService.deleteGroupSubject(id);
    }

}
