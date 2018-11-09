package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.LectureTimeViewModel;

import java.util.List;

public interface LectureTimeDataService {
    List<LectureTimeViewModel> getAllLectureTimes();

    LectureTimeViewModel getLectureTimeById(Integer id);

    LectureTimeViewModel saveLectureTime(LectureTimeViewModel lectureTime);

    void deleteLectureTime(Integer id);
}
