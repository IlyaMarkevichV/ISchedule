package com.netcracker.edu.fapi.service.impl;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.netcracker.edu.fapi.models.FacultyViewModel;
import com.netcracker.edu.fapi.service.FacultyDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FacultyDataServiceImpl implements FacultyDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<FacultyViewModel> getAllFaculties() {
        RestTemplate restTemplate = new RestTemplate();
        FacultyViewModel[] faculties = restTemplate.getForObject(
                backendServerUrl + "/api/faculties",
                FacultyViewModel[].class);
        return faculties == null ? Collections.emptyList() : Arrays.asList(faculties);
    }

    @Override
    public FacultyViewModel getFacultyById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        FacultyViewModel[] faculties = restTemplate.getForObject(
                backendServerUrl + "/api/faculties",
                FacultyViewModel[].class);

        if (faculties != null) {
            for (FacultyViewModel faculty : faculties) {
                if (faculty.getFacultyId() == id)
                    return faculty;
            }
        }
        return null;
    }

    @Override
    public FacultyViewModel saveFaculty(FacultyViewModel faculty) {
        if (faculty != null) {
            return new RestTemplate().postForEntity(
                    backendServerUrl + "/api/faculties",
                    faculty,
                    FacultyViewModel.class
            ).getBody();
        }
        return null;
    }

    @Override
    public void deleteFaculty(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/faculties/" + id);
    }
}
