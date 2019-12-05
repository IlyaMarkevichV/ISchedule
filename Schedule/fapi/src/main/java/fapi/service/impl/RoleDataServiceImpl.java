package fapi.service.impl;

import fapi.models.RoleViewModel;
import fapi.service.RoleDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RoleDataServiceImpl implements RoleDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<RoleViewModel> getAllRoles() {
        RestTemplate restTemplate = new RestTemplate();
        RoleViewModel[] roles = restTemplate.getForObject(
                backendServerUrl + "/api/roles",
                RoleViewModel[].class);
        return roles == null ? Collections.emptyList() : Arrays.asList(roles);
    }
}
