package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.LessonViewModel;

import java.util.List;

public interface LessonDataService {
    List<LessonViewModel> getAllLessons();

    LessonViewModel getLessonById(Integer id);

    LessonViewModel saveLesson(LessonViewModel schedule);

    void deleteLesson(Integer id);
}
