package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.AccountViewModel;

import java.util.List;

public interface AccountDataService {
    AccountViewModel getAccountByLogin(String login);

    AccountViewModel getAccountById(Integer id);

    Integer validatePass(AccountViewModel accountViewModel);
}
