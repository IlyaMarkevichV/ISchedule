package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Schedule;
import com.netcracker.edu.backend.entity.Subject;

import java.util.Optional;

public interface ScheduleService {
    Schedule saveLectureInSchedule(Schedule schedule);

    Optional<Schedule> getLectureFromScheduleById(Integer id);

    Iterable<Schedule> getAllLecturesFromSchedule();

    void deleteLectureFromSchedule(Integer id);
}
