package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.AccountViewModel;
import com.netcracker.edu.fapi.models.StudentGroupViewModel;
import com.netcracker.edu.fapi.service.AccountDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AccountDataServiceImpl implements AccountDataService {

    @Value("${backend.server.url}")
    private String backendUrlServer;

    @Override
    public List<AccountViewModel> getAllAccounts(String userRole) {
        RestTemplate restTemplate = new RestTemplate();
        AccountViewModel[] accounts;

        switch (userRole) {
            case "student": {
                /* RestTemplate always return StudentGroup in order to give information
                 * about group into which exists student. Therefore we convert
                 * from StudentGroup to Account(present Student and Professor too)
                 * using getAccount() method into StudentGroup
                 */
                StudentGroupViewModel[] studentGroupViewModels =
                        restTemplate.getForObject(
                                backendUrlServer + "/api/students",
                                StudentGroupViewModel[].class);

                if (studentGroupViewModels == null || studentGroupViewModels.length == 0)
                    return Collections.emptyList();

                ArrayList<AccountViewModel> accountList = new ArrayList<AccountViewModel>();
                for (StudentGroupViewModel studentGroup : studentGroupViewModels) {
                    accountList.add(studentGroup.getAccount());
                }

                return accountList;
            }
            case "professor": {
                accounts = restTemplate.getForObject(
                        backendUrlServer + "/api/professors",
                        AccountViewModel[].class);
                return accounts == null ? Collections.emptyList() : Arrays.asList(accounts);
            }
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public AccountViewModel saveAccount(AccountViewModel account) {
        if (account == null)
            return null;

        switch (account.getRole()) {
            case "student": {
                return new RestTemplate().postForEntity(
                        backendUrlServer + "/api/students",
                        account,
                        AccountViewModel.class).getBody();
            }
            case "professor": {
                return new RestTemplate().postForEntity(
                        backendUrlServer + "/api/professors",
                        account,
                        AccountViewModel.class).getBody();
            }
            default:
                return null;
        }
    }

    @Override
    public void deleteAccount(Integer id, String userRole) {
        switch (userRole) {
            case "student": {
                new RestTemplate().delete(backendUrlServer + "/api/students/" + id);
            }
            break;
            case "professor": {
                new RestTemplate().delete(backendUrlServer + "/api/professors/" + id);
            }
            break;
            default:
                return;
        }
    }
}
