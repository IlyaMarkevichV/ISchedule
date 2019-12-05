package fapi.service;

import fapi.models.LessonTimeViewModel;

import java.util.List;

public interface LessonTimeDataService {
    List<LessonTimeViewModel> getAllLessonTimes();
}
