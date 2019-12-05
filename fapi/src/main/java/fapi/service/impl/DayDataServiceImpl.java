package fapi.service.impl;

import fapi.models.DayViewModel;
import fapi.service.DayDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DayDataServiceImpl implements DayDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<DayViewModel> getAllDays() {
        RestTemplate restTemplate = new RestTemplate();
        DayViewModel[] days = restTemplate.getForObject(
                backendServerUrl + "/api/days",
                DayViewModel[].class);
        return days == null ? Collections.emptyList() : Arrays.asList(days);
    }
}
