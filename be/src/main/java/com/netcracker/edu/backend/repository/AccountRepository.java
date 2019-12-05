package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> getAccountsByRole(Role role);

    Optional<Account> getAccountByLoginAndPassword(String login, String password);

    Optional<Account> getAccountByLogin(String login);
}
