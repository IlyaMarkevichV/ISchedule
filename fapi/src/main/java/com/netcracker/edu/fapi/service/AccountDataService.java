package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.AccountViewModel;

import java.util.List;

public interface AccountDataService {
    List<AccountViewModel> getAllAccounts(String userRole);

    AccountViewModel saveAccount(AccountViewModel account);

    void deleteAccount(Integer id, String userRole);

    AccountViewModel getAccountByLogin(String login);

    AccountViewModel getAccountById(Integer id);
}
