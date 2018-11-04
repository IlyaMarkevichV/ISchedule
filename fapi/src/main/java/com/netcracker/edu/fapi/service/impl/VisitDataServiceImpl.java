package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.VisitViewModel;
import com.netcracker.edu.fapi.service.VisitDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class VisitDataServiceImpl implements VisitDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<VisitViewModel> getAllVisits() {
        RestTemplate restTemplate = new RestTemplate();
        VisitViewModel[] visits = restTemplate.getForObject(
                backendServerUrl + "/api/visit-entities",
                VisitViewModel[].class
        );

        return visits == null ? Collections.emptyList() : Arrays.asList(visits);
    }

    @Override
    public VisitViewModel getVisitById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        VisitViewModel[] visits = restTemplate.getForObject(
                backendServerUrl + "/api/visit-entities",
                VisitViewModel[].class
        );

        if (visits != null) {
            for (VisitViewModel visit : visits) {
                if (visit.getId() == id) {
                    return visit;
                }
            }
        }
        return null;
    }

    @Override
    public VisitViewModel saveVisit(VisitViewModel visit) {
        return new RestTemplate().postForEntity(
                backendServerUrl + "/api/visit-entities",
                visit,
                VisitViewModel.class
        ).getBody();
    }

    @Override
    public void deleteVisit(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/visit-entities" + id);
    }
}
