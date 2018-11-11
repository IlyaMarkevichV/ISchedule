package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
