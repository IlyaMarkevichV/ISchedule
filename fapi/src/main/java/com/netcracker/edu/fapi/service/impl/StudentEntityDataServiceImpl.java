package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.StudentEntityViewModel;
import com.netcracker.edu.fapi.service.StudentEntityDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StudentEntityDataServiceImpl implements StudentEntityDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<StudentEntityViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        StudentEntityViewModel[] studentEntityViewModelResponse =
                restTemplate.getForObject(backendServerUrl + "/api/student-entities/", StudentEntityViewModel[].class);
        return studentEntityViewModelResponse == null ? Collections.emptyList() : Arrays.asList(studentEntityViewModelResponse);
    }

    // TODO: write function StudentEntityViewModel getStudentEntityById(Integer id) implementation
    @Override
    public StudentEntityViewModel getStudentEntityById(Integer id) {
        return null;
    }

    @Override
    public StudentEntityViewModel saveStudentEntity(StudentEntityViewModel entityViewModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/student-entities", entityViewModel, StudentEntityViewModel.class).getBody();
    }

    @Override
    public void deleteStudentEntity(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/student-entities/" + id);
    }
}
