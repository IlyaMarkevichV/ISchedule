package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.StudentViewModel;
import com.netcracker.edu.fapi.service.StudentDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StudentDataServiceImpl implements StudentDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<StudentViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        StudentViewModel[] studentViewModelResponse =
                restTemplate.getForObject(backendServerUrl + "/api/students/", StudentViewModel[].class);
        return studentViewModelResponse == null ? Collections.emptyList() : Arrays.asList(studentViewModelResponse);
    }


    @Override
    public StudentViewModel saveStudent(StudentViewModel viewModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/students", viewModel, StudentViewModel.class).getBody();
    }

    @Override
    public void deleteStudent(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/students/" + id);
    }
}
