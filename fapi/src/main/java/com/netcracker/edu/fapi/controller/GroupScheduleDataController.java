package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.GroupScheduleViewModel;
import com.netcracker.edu.fapi.service.GroupScheduleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ba-group-schedule")
public class GroupScheduleDataController {

    private GroupScheduleDataService groupScheduleDataService;

    @Autowired
    public GroupScheduleDataController(GroupScheduleDataService groupScheduleDataService) {
        this.groupScheduleDataService = groupScheduleDataService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupScheduleViewModel> getLectureOfGroupById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(groupScheduleDataService.getLectureOfGroupById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<GroupScheduleViewModel>> getAllLecturesOfGroups() {
        return ResponseEntity.ok(groupScheduleDataService.getAllLecturesOfGroups());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public GroupScheduleViewModel saveLectureOfGroup(@RequestBody GroupScheduleViewModel groupScheduleViewModel) {
        return groupScheduleViewModel != null ? groupScheduleDataService.saveLectureOfGroup(groupScheduleViewModel) : null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void deleteLectureOfGroup(@PathVariable(name = "id") Integer id) {
        groupScheduleDataService.deleteLectureOfGroup(id);
    }
}
