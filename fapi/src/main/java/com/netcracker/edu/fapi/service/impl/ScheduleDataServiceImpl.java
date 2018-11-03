package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.ScheduleViewModel;
import com.netcracker.edu.fapi.service.ScheduleDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ScheduleDataServiceImpl implements ScheduleDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<ScheduleViewModel> getAllLecturesFromSchedule() {
        RestTemplate restTemplate = new RestTemplate();
        ScheduleViewModel[] scheduleViewModels = restTemplate.getForObject(
                backendServerUrl + "api/schedule-lectures",
                ScheduleViewModel[].class
        );
        return scheduleViewModels == null ? Collections.emptyList() : Arrays.asList(scheduleViewModels);
    }

    @Override
    public ScheduleViewModel getLectureFromScheduleById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ScheduleViewModel[] scheduleViewModels = restTemplate.getForObject(
                backendServerUrl + "api/schedule-lectures",
                ScheduleViewModel[].class
        );

        if (scheduleViewModels != null) {
            for (ScheduleViewModel scheduleViewModel : scheduleViewModels) {
                if (scheduleViewModel.getScheduleId() == id)
                    return scheduleViewModel;
            }
        }
        return null;
    }

    @Override
    public ScheduleViewModel saveLectureInSchedule(ScheduleViewModel schedule) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "api/schedule-lectures",
                schedule,
                ScheduleViewModel.class).getBody();
    }

    @Override
    public void deleteLectureFormSchedule(Integer id) {
        new RestTemplate().delete(backendServerUrl + "api/schedule-lectures");
    }
}
