package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.AccountViewModel;
import com.netcracker.edu.fapi.service.AccountDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/ba-accounts")
public class AccountDataController {

    private AccountDataService accountDataService;

    @Autowired
    public AccountDataController(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity<AccountViewModel> getAccountByLoginAndPassword(@RequestParam(name = "login") String login) {
        AccountViewModel account = accountDataService.getAccountByLogin(login);
        return ResponseEntity.ok(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountViewModel> getAccountById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(accountDataService.getAccountById(id));
    }
}
