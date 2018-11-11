package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.AccountViewModel;
import com.netcracker.edu.fapi.service.AccountDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AccountDataServiceImpl implements AccountDataService {

    @Value("${backend.server.url}")
    private String backendUrlServer;

    @Override
    public List<AccountViewModel> getAllAccounts() {
        RestTemplate restTemplate = new RestTemplate();
        AccountViewModel[] accounts = restTemplate.getForObject(
                backendUrlServer + "/api/account-entities",
                AccountViewModel[].class);
        return accounts == null ? Collections.emptyList() : Arrays.asList(accounts);
    }

    @Override
    public AccountViewModel getAccountById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        AccountViewModel[] accounts = restTemplate.getForObject(
                backendUrlServer + "/api/account-entities",
                AccountViewModel[].class);
        if (accounts != null) {
            for (AccountViewModel account : accounts) {
                if (account.getAccountId() == id)
                    return account;
            }
        }
        return null;
    }

    @Override
    public AccountViewModel saveAccount(AccountViewModel account) {
        return new RestTemplate().postForEntity(
                backendUrlServer + "/api/account-entities",
                account,
                AccountViewModel.class).getBody();
    }

    @Override
    public void deleteAccount(Integer id) {
        new RestTemplate().delete(backendUrlServer + "/api/account-entities/" + id);
    }
}
