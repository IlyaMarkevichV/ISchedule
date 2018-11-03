package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.GroupInfo;
import com.netcracker.edu.backend.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/group-info-entities")
public class GroupInfoController {

    private GroupInfoService groupInfoService;

    @Autowired
    public GroupInfoController(GroupInfoService groupInfoService) {
        this.groupInfoService = groupInfoService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupInfo> getGroupInfoById(@PathVariable(name = "id") Integer id) {
        Optional<GroupInfo> groupInfo = groupInfoService.getGroupInfoById(id);
        return groupInfo.isPresent() ? ResponseEntity.ok(groupInfo.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<GroupInfo> getAllGroupInfo() {
        return groupInfoService.getAllGroupInfo();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public GroupInfo saveGroupInfo(@RequestBody GroupInfo info) {
        return groupInfoService.saveGroupInfo(info);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroupInfo(@PathVariable(name = "id") Integer id) {
        groupInfoService.deleteGroupInfo(id);
        return ResponseEntity.noContent().build();
    }
}
