package backend.service;

import backend.entity.LessonInfo;

public interface LessonInfoService {
    Iterable<LessonInfo> getAllLessonInfos();
}
