package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.LessonViewModel;
import com.netcracker.edu.fapi.service.LessonDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LessonDataServiceImpl implements LessonDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<LessonViewModel> getAllLessons() {
        RestTemplate restTemplate = new RestTemplate();
        LessonViewModel[] lessonViewModels = restTemplate.getForObject(
                backendServerUrl + "api/lessons",
                LessonViewModel[].class
        );
        return lessonViewModels == null ? Collections.emptyList() : Arrays.asList(lessonViewModels);
    }

    @Override
    public LessonViewModel getLessonById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        LessonViewModel[] lessonViewModels = restTemplate.getForObject(
                backendServerUrl + "api/lessons",
                LessonViewModel[].class
        );

        if (lessonViewModels != null) {
            for (LessonViewModel lessonViewModel : lessonViewModels) {
                if (lessonViewModel.getLessonId() == id)
                    return lessonViewModel;
            }
        }
        return null;
    }

    @Override
    public LessonViewModel saveLesson(LessonViewModel schedule) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "api/lessons",
                schedule,
                LessonViewModel.class).getBody();
    }

    @Override
    public void deleteLesson(Integer id) {
        new RestTemplate().delete(backendServerUrl + "api/schedule-lectures");
    }
}
