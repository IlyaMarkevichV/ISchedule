package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.LectureTime;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface LectureTimeService {
    LectureTime saveLectureTime(LectureTime lectureTime);

    Optional<LectureTime> getLectureById(Integer id);

    Iterable<LectureTime> getAllLectureTimes();

    void deleteLectureTime(Integer id);
}
