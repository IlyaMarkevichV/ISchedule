package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.GroupSchedule;

import java.util.Optional;

public interface GroupScheduleService {
    GroupSchedule saveLectureOfGroup(GroupSchedule groupSchedule);

    Optional<GroupSchedule> getLectureOfGroupById(Integer id);

    Iterable<GroupSchedule> getAllLecturesOfGroups();

    void deleteLectureOfGroup(Integer id);
}
