package backend.service.impl;

import backend.entity.Account;
import backend.entity.Role;
import backend.repository.AccountRepository;
import backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public void deleteAccount(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Account> getAccountsByRole(Role role) {
        return repository.getAccountsByRole(role);
    }

    @Override
    public Optional<Account> getAccountByLogin(String login) {
        return repository.getAccountByLogin(login);
    }
}
