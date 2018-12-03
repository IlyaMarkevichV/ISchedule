package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.ProfessorViewModel;
import com.netcracker.edu.fapi.service.ProfessorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProfessorDataServiceImpl implements ProfessorDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public ProfessorDataServiceImpl(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public ProfessorViewModel saveProfessor(ProfessorViewModel entityViewModel) {
        String encodedPassword = encoder.encode(entityViewModel.getAccount().getPassword());
        entityViewModel.getAccount().setPassword(encodedPassword);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/professors", entityViewModel, ProfessorViewModel.class).getBody();
    }

    @Override
    public void deleteProfessor(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/professors/" + id);
    }

    @Override
    public RestPageImpl<ProfessorViewModel> getPage(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendServerUrl + "/api/professors?" + request.getQueryString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RestPageImpl<ProfessorViewModel>>() {}).getBody();
    }
}
