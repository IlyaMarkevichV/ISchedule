package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.GroupSubjectViewModel;
import com.netcracker.edu.fapi.service.GroupSubjectDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GroupSubjectDataServiceImpl implements GroupSubjectDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<GroupSubjectViewModel> getAllGroupSubjects() {
        RestTemplate restTemplate = new RestTemplate();
        GroupSubjectViewModel[] subjects = restTemplate.getForObject(
                backendServerUrl + "/api/group-subject-entities",
                GroupSubjectViewModel[].class);
        return subjects == null ? Collections.emptyList() : Arrays.asList(subjects);
    }

    @Override
    public GroupSubjectViewModel getGroupSubjectById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        GroupSubjectViewModel[] subjects = restTemplate.getForObject(
                backendServerUrl + "/api/group-subject-entities",
                GroupSubjectViewModel[].class);
        if (subjects != null) {
            for (GroupSubjectViewModel subject : subjects) {
                if (subject.getId() == id)
                    return subject;
            }
        }
        return null;
    }

    @Override
    public GroupSubjectViewModel saveGroupSubject(GroupSubjectViewModel subjectViewModel) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "/api/group-subject-entities",
                subjectViewModel,
                GroupSubjectViewModel.class).getBody();
    }

    @Override
    public void deleteGroupSubject(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/group-subject-entities" + id);
    }
}
