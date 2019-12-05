package fapi.service;

import fapi.models.AccountViewModel;

public interface AccountDataService {
    AccountViewModel getAccountByLogin(String login);

    AccountViewModel getAccountById(Integer id);

    Integer validatePass(AccountViewModel accountViewModel);
}
