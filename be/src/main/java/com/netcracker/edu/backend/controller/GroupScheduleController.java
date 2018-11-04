package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.GroupSchedule;
import com.netcracker.edu.backend.service.GroupScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/group-schedule-entities")
public class GroupScheduleController {

    private GroupScheduleService groupScheduleService;

    @Autowired
    public GroupScheduleController(GroupScheduleService groupScheduleService) {
        this.groupScheduleService = groupScheduleService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupSchedule> getLectureOfGroupById(@PathVariable(name = "id") Integer id) {
        Optional<GroupSchedule> groupSchedule = groupScheduleService.getLectureOfGroupById(id);
        return groupSchedule.isPresent() ? ResponseEntity.ok(groupSchedule.get()) : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<GroupSchedule> getAllLecturesOfGroups() {
        return groupScheduleService.getAllLecturesOfGroups();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public GroupSchedule saveLectureOfGroup(@RequestBody GroupSchedule groupSchedule) {
        return groupScheduleService.saveLectureOfGroup(groupSchedule);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteLectureOfGroup(@PathVariable(name = "id") Integer id) {
        groupScheduleService.deleteLectureOfGroup(id);
        return ResponseEntity.noContent().build();
    }
}
