package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.GroupViewModel;
import com.netcracker.edu.fapi.service.GroupDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GroupDataServiceImpl implements GroupDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<GroupViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        GroupViewModel[] groupViewModels = restTemplate.getForObject(backendServerUrl + "/api/group-entities", GroupViewModel[].class);
        return groupViewModels == null ? Collections.emptyList() : Arrays.asList(groupViewModels);
    }

    @Override
    public GroupViewModel getGroupById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        GroupViewModel[] groupViewModels = restTemplate.getForObject(backendServerUrl + "/api/group-entities", GroupViewModel[].class);

        if (groupViewModels != null) {
            for (GroupViewModel groupViewModel : groupViewModels) {
                if (groupViewModel.getGroupId() == id)
                    return groupViewModel;
            }
        }
        return null;
    }

    @Override
    public GroupViewModel saveGroup(GroupViewModel groupViewModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/group-entities", groupViewModel, GroupViewModel.class).getBody();
    }

    @Override
    public void deleteGroup(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/group-entities/" + id);
    }
}
