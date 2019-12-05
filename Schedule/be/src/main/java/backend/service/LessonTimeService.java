package backend.service;

import backend.entity.LessonTime;

public interface LessonTimeService {
    Iterable<LessonTime> getAllLessonTimes();
}
