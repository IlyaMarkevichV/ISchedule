package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.LessonViewModel;
import com.netcracker.edu.fapi.service.LessonDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
        LessonViewModel lessonViewModel = restTemplate.getForObject(
                backendServerUrl + "api/lessons/" + id,
                LessonViewModel.class
        );
        return lessonViewModel;
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
        new RestTemplate().delete(backendServerUrl + "api/lessons/" + id);
    }

    @Override
    public List<LessonViewModel> getPage(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        LessonViewModel[] lessonViewModels = restTemplate.getForObject(
                backendServerUrl + "api/lessons/pages?" + request.getQueryString(),
                LessonViewModel[].class
        );

        return lessonViewModels == null ? Collections.emptyList() : Arrays.asList(lessonViewModels);
    }
}
