package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.GroupScheduleViewModel;
import com.netcracker.edu.fapi.service.GroupScheduleDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GroupScheduleDataServiceImpl implements GroupScheduleDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<GroupScheduleViewModel> getAllLecturesOfGroups() {
        RestTemplate restTemplate = new RestTemplate();
        GroupScheduleViewModel[] groupScheduleViewModels = restTemplate.getForObject(
                backendServerUrl + "/api/group-schedule-entities",
                GroupScheduleViewModel[].class);
        return groupScheduleViewModels == null ? Collections.emptyList() : Arrays.asList(groupScheduleViewModels);
    }

    @Override
    public GroupScheduleViewModel getLectureOfGroupById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        GroupScheduleViewModel[] groupScheduleViewModels = restTemplate.getForObject(
                backendServerUrl + "/api/group-schedule-entities",
                GroupScheduleViewModel[].class);

        if (groupScheduleViewModels != null) {
            for (GroupScheduleViewModel groupScheduleViewModel : groupScheduleViewModels) {
                if (groupScheduleViewModel.getId() == id) {
                    return groupScheduleViewModel;
                }
            }
        }
        return null;
    }

    @Override
    public GroupScheduleViewModel saveLectureOfGroup(GroupScheduleViewModel groupScheduleViewModel) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "/api/group-schedule-entities",
                groupScheduleViewModel,
                GroupScheduleViewModel.class).getBody();
    }

    @Override
    public void deleteLectureOfGroup(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/group-schedule-entities/" + id);
    }
}
