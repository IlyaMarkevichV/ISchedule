package fapi.service.impl;

import fapi.models.RestPageImpl;
import fapi.models.StudentViewModel;
import fapi.service.StudentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
public class StudentDataServiceImpl implements StudentDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public StudentDataServiceImpl(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public StudentViewModel saveStudent(StudentViewModel viewModel) {
        String encodedPassword = encoder.encode(viewModel.getAccount().getPassword());
        viewModel.getAccount().setPassword(encodedPassword);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/students", viewModel, StudentViewModel.class).getBody();
    }

    @Override
    public void deleteStudent(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/students/" + id);
    }

    @Override
    public RestPageImpl<StudentViewModel> getPage(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                backendServerUrl + "/api/students?" + request.getQueryString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RestPageImpl<StudentViewModel>>() {}).getBody();
    }

    @Override
    public StudentViewModel getStudentByLogin(String login) {
        return new RestTemplate().getForObject(
                backendServerUrl + "/api/students/search?login=" + login,
                StudentViewModel.class);
    }
}
