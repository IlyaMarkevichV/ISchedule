package fapi.service;

import fapi.models.LessonTimeViewModel;

import java.util.List;

public interface LectureTimeDataService {
    List<LessonTimeViewModel> getAllLectureTimes();

    LessonTimeViewModel getLectureTimeById(Integer id);

    LessonTimeViewModel saveLectureTime(LessonTimeViewModel lectureTime);

    void deleteLectureTime(Integer id);
}
