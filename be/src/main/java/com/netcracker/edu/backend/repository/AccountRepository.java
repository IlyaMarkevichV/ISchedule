package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> getAccountsByRole(String userRoleName);

    Optional<Account> getAccountByLoginAndPassword(String login, String password);
}
