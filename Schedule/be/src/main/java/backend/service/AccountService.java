package backend.service;

import backend.entity.Account;
import backend.entity.Role;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account saveAccount(Account account);

    Optional<Account> getAccountById(Integer id);

    Iterable<Account> getAllAccounts();

    void deleteAccount(Integer id);

    List<Account> getAccountsByRole(Role ole);

    Optional<Account> getAccountByLogin(String login);
}
