package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.LessonInfoViewModel;
import com.netcracker.edu.fapi.service.LessonInfoDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LessonInfoDataServiceImpl implements LessonInfoDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<LessonInfoViewModel> getAllLessonInfos() {
        RestTemplate restTemplate = new RestTemplate();
        LessonInfoViewModel[] infos = restTemplate.getForObject(
                backendServerUrl + "/api/lesson-infos",
                LessonInfoViewModel[].class);
        return infos == null ? Collections.emptyList() : Arrays.asList(infos);
    }
}
