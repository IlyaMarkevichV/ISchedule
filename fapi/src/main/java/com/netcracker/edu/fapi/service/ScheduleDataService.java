package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ScheduleViewModel;

import java.util.List;

public interface ScheduleDataService {
    List<ScheduleViewModel> getAllLecturesFromSchedule();

    ScheduleViewModel getLectureFromScheduleById(Integer id);

    ScheduleViewModel saveLectureInSchedule(ScheduleViewModel schedule);

    void deleteLectureFormSchedule(Integer id);
}
