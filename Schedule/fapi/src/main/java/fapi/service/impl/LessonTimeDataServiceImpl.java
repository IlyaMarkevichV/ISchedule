package fapi.service.impl;

import fapi.models.LessonTimeViewModel;
import fapi.service.LessonTimeDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LessonTimeDataServiceImpl implements LessonTimeDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<LessonTimeViewModel> getAllLessonTimes() {
        RestTemplate restTemplate = new RestTemplate();
        LessonTimeViewModel[] times = restTemplate.getForObject(
                backendServerUrl + "/api/lesson-times",
                LessonTimeViewModel[].class);
        return times == null ? Collections.emptyList() : Arrays.asList(times);
    }
}
