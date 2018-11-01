package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.ProfessorViewModel;
import com.netcracker.edu.fapi.service.ProfessorDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProfessorDataServiceImpl implements ProfessorDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<ProfessorViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ProfessorViewModel[] studentEntityViewModelsResponse =
                restTemplate.getForObject(backendServerUrl + "api/professor-entities/", ProfessorViewModel[].class);
        return studentEntityViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(studentEntityViewModelsResponse);
    }

    @Override
    public ProfessorViewModel getProfessorById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ProfessorViewModel[] professorViewModelsResponse = restTemplate.getForObject(backendServerUrl + "api/professor-entities/", ProfessorViewModel[].class);

        if (professorViewModelsResponse != null) {
            for (ProfessorViewModel entityViewModel : professorViewModelsResponse) {
                if (entityViewModel.getProfessorId() == id)
                    return entityViewModel;
            }
        }
        return null;
    }

    @Override
    public ProfessorViewModel saveProfessor(ProfessorViewModel entityViewModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/professor-entities", entityViewModel, ProfessorViewModel.class).getBody();
    }

    @Override
    public void deleteProfessor(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/professor-entities/" + id);
    }
}
