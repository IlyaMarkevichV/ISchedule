package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.GroupInfoViewModel;
import com.netcracker.edu.fapi.service.GroupInfoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.acl.Group;
import java.util.List;

@RestController
@RequestMapping("api/ba-group-info")
public class GroupInfoDataController {
    private GroupInfoDataService groupInfoDataService;

    @Autowired
    public GroupInfoDataController(GroupInfoDataService groupInfoDataService) {
        this.groupInfoDataService = groupInfoDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupInfoViewModel> getGroupInfoById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(groupInfoDataService.getGroupInfoById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<GroupInfoViewModel>> getGroupsInfo() {
        return ResponseEntity.ok(groupInfoDataService.getAllGroupInfo());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<GroupInfoViewModel> saveGroupInfo(@RequestBody GroupInfoViewModel groupInfo) {
        if (groupInfo != null)
            return ResponseEntity.ok(groupInfoDataService.saveGroupInfo(groupInfo));
        else
            return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGroupInfo(@PathVariable(name = "id") Integer id) {
        groupInfoDataService.deleteGroupInfo(id);
    }
}
