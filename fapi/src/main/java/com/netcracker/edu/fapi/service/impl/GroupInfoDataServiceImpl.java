package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.GroupInfoViewModel;
import com.netcracker.edu.fapi.service.GroupInfoDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GroupInfoDataServiceImpl implements GroupInfoDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<GroupInfoViewModel> getAllGroupInfo() {
        RestTemplate restTemplate = new RestTemplate();
        GroupInfoViewModel[] groupInfos = restTemplate.getForObject(
                backendServerUrl + "/api/group-info-entities",
                GroupInfoViewModel[].class);
        return groupInfos == null ? Collections.emptyList() : Arrays.asList(groupInfos);
    }

    @Override
    public GroupInfoViewModel getGroupInfoById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        GroupInfoViewModel[] groupInfos = restTemplate.getForObject(
                backendServerUrl + "/api/group-info-entities",
                GroupInfoViewModel[].class);
        if (groupInfos != null) {
            for (GroupInfoViewModel groupInfo : groupInfos) {
                if (groupInfo.getGroupInfoId() == id)
                    return groupInfo;
            }
        }
        return null;
    }

    @Override
    public GroupInfoViewModel saveGroupInfo(GroupInfoViewModel groupInfof) {
        return new RestTemplate().postForObject(
                backendServerUrl + "/api/group-info-entities",
                groupInfof,
                GroupInfoViewModel.class
        );
    }

    @Override
    public void deleteGroupInfo(Integer id) {
        new RestTemplate().delete(backendServerUrl + "/api/group-info-entities" + id);
    }
}
