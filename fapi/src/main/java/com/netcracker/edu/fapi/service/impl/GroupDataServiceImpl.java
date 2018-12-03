package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.GroupViewModel;
import com.netcracker.edu.fapi.service.GroupDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GroupDataServiceImpl implements GroupDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public GroupViewModel getGroupById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                backendServerUrl + "/api/groups/" + id,
                GroupViewModel.class);
    }

    @Override
    public GroupViewModel saveGroup(GroupViewModel groupViewModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/groups", groupViewModel, GroupViewModel.class).getBody();
    }

    @Override
    public void deleteGroup(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/groups/" + id);
    }

    @Override
    public RestPageImpl<GroupViewModel> getPage(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendServerUrl + "/api/groups?" + request.getQueryString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RestPageImpl<GroupViewModel>>() {}
        ).getBody();
    }
}
