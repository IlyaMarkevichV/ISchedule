package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.GroupScheduleViewModel;

import java.util.List;

public interface GroupScheduleDataService {
    List<GroupScheduleViewModel> getAllLecturesOfGroups();

    GroupScheduleViewModel getLectureOfGroupById(Integer id);

    GroupScheduleViewModel saveLectureOfGroup(GroupScheduleViewModel groupScheduleViewModel);

    void deleteLectureOfGroup(Integer id);
}
