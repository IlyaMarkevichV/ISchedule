package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.LessonTimeViewModel;
import com.netcracker.edu.fapi.service.LectureTimeDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LectureTimeDataServiceImpl implements LectureTimeDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<LessonTimeViewModel> getAllLectureTimes() {
        RestTemplate restTemplate = new RestTemplate();
        LessonTimeViewModel[] times = restTemplate.getForObject(
                backendServerUrl + "/api/lecture-time",
                    LessonTimeViewModel[].class);
        return times == null ? Collections.emptyList() : Arrays.asList(times);
    }

    @Override
    public LessonTimeViewModel getLectureTimeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        LessonTimeViewModel[] times = restTemplate.getForObject(
                backendServerUrl + "/api/lecture-time",
                LessonTimeViewModel[].class);
        if (times != null) {
            for (LessonTimeViewModel time : times) {
                if (time.getId() == id)
                    return time;
            }
        }
        return null;
    }

    @Override
    public LessonTimeViewModel saveLectureTime(LessonTimeViewModel lectureTime) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "/api/lecture-time",
                lectureTime,
                LessonTimeViewModel.class).getBody();
    }

    @Override
    public void deleteLectureTime(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/lecture-time" + id);
    }
}
