package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.SubjectViewModel;
import com.netcracker.edu.fapi.service.SubjectDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SubjectDataServiceImpl implements SubjectDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<SubjectViewModel> getAllSubjects() {
        RestTemplate restTemplate = new RestTemplate();
        SubjectViewModel[] subjects = restTemplate.getForObject(backendServerUrl + "/api/subject-entities", SubjectViewModel[].class);
        return subjects == null ? Collections.emptyList() : Arrays.asList(subjects);
    }

    @Override
    public SubjectViewModel getSubjectById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        SubjectViewModel[] subjects = restTemplate.getForObject(backendServerUrl + "/api/subject-entities", SubjectViewModel[].class);

        if (subjects != null) {
            for (SubjectViewModel subject : subjects) {
                if (subject.getId() == id)
                    return subject;
            }
        }
        return null;
    }

    @Override
    public SubjectViewModel saveSubject(SubjectViewModel subject) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subject-entities", subject, SubjectViewModel.class).getBody();
    }

    @Override
    public void deleteSubject(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subject-entities/" + id);
    }
}
