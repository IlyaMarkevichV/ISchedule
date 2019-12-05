package fapi.controller;

import fapi.models.AccountViewModel;
import fapi.service.AccountDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<Integer> validatePassword(@RequestBody AccountViewModel accountViewModel) {
        return ResponseEntity.ok(accountDataService.validatePass(accountViewModel));
    }
}
