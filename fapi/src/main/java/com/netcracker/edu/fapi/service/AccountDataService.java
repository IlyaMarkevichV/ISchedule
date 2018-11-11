package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.AccountViewModel;

import java.util.List;

public interface AccountDataService {
    List<AccountViewModel> getAllAccounts();

    AccountViewModel getAccountById(Integer id);

    AccountViewModel saveAccount(AccountViewModel account);

    void deleteAccount(Integer id);
}
