package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.GroupViewModel;
import com.netcracker.edu.fapi.service.GroupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ba-group")
public class GroupDataController {

    private GroupDataService groupService;

    @Autowired
    public GroupDataController(GroupDataService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupViewModel> getGroupById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<GroupViewModel>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAll());
    }

    // TODO: server validation
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<GroupViewModel> saveGroup(@RequestBody GroupViewModel groupViewModel) {
        if (groupViewModel != null) {
            return ResponseEntity.ok(groupService.saveGroup(groupViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable(name = "id")  Integer id) {
        groupService.deleteGroup(id);
    }
}
