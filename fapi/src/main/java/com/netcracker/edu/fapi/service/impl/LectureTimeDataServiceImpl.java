package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.LectureTimeViewModel;
import com.netcracker.edu.fapi.service.LectureTimeDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LectureTimeDataServiceImpl implements LectureTimeDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<LectureTimeViewModel> getAllLectureTimes() {
        RestTemplate restTemplate = new RestTemplate();
        LectureTimeViewModel[] times = restTemplate.getForObject(
                backendServerUrl + "/api/lecture-time",
                    LectureTimeViewModel[].class);
        return times == null ? Collections.emptyList() : Arrays.asList(times);
    }

    @Override
    public LectureTimeViewModel getLectureTimeById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        LectureTimeViewModel[] times = restTemplate.getForObject(
                backendServerUrl + "/api/lecture-time",
                LectureTimeViewModel[].class);
        if (times != null) {
            for (LectureTimeViewModel time : times) {
                if (time.getId() == id)
                    return time;
            }
        }
        return null;
    }

    @Override
    public LectureTimeViewModel saveLectureTime(LectureTimeViewModel lectureTime) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "/api/lecture-time",
                lectureTime,
                LectureTimeViewModel.class).getBody();
    }

    @Override
    public void deleteLectureTime(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/lecture-time" + id);
    }
}
