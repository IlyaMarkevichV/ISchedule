package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.FapiApplication;
import com.netcracker.edu.fapi.models.AccountViewModel;
import com.netcracker.edu.fapi.models.StudentGroupViewModel;
import com.netcracker.edu.fapi.service.AccountDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service("userDataService")
public class AccountDataServiceImpl implements AccountDataService, UserDetailsService {

    @Value("${backend.server.url}")
    private String backendUrlServer;



    @Override
    public List<AccountViewModel> getAllAccounts(String userRole) {
        RestTemplate restTemplate = new RestTemplate();
        AccountViewModel[] accounts;

        switch (userRole) {
            case "student": {
                /* RestTemplate always return StudentGroup in order to give information
                 * about group into which exists student. Therefore we convert
                 * from StudentGroup to Account(present Student and Professor too)
                 * using getAccount() method into StudentGroup
                 */
                StudentGroupViewModel[] studentGroupViewModels =
                        restTemplate.getForObject(
                                backendUrlServer + "/api/students",
                                StudentGroupViewModel[].class);

                if (studentGroupViewModels == null || studentGroupViewModels.length == 0)
                    return Collections.emptyList();

                ArrayList<AccountViewModel> accountList = new ArrayList<AccountViewModel>();
                /*convert data*/
                for (StudentGroupViewModel studentGroup : studentGroupViewModels) {
                    AccountViewModel tempAcc = studentGroup.getAccount();
                    tempAcc.setStudentGroupId(studentGroup.getId());
                    tempAcc.getStudentProfessor().setGroup(studentGroup.getGroup());
                    accountList.add(tempAcc);
                }

                return accountList;
            }
            case "administrator":
            case "professor": {
                accounts = restTemplate.getForObject(
                        backendUrlServer + "/api/professors",
                        AccountViewModel[].class);
                return accounts == null ? Collections.emptyList() : Arrays.asList(accounts);
            }
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public AccountViewModel saveAccount(AccountViewModel account) {
        if (account == null)
            return null;

        switch (account.getRole()) {
            case "student": {
                return new RestTemplate().postForEntity(
                        backendUrlServer + "/api/students",
                        account,
                        AccountViewModel.class).getBody();
            }
            case "administrator":
            case "professor": {
                return new RestTemplate().postForEntity(
                        backendUrlServer + "/api/professors",
                        account,
                        AccountViewModel.class).getBody();
            }
            default:
                return null;
        }
    }

    @Override
    public void deleteAccount(Integer id, String userRole) {
        switch (userRole) {
            case "student": {
                new RestTemplate().delete(backendUrlServer + "/api/students/" + id);
            }
            break;
            case "administrator":
            case "professor": {
                new RestTemplate().delete(backendUrlServer + "/api/professors/" + id);
            }
            break;
            default:
                return;
        }
    }

    @Override
    public AccountViewModel getAccountByLogin(@NotNull String login) {
        ResponseEntity<AccountViewModel> acc = new RestTemplate().getForEntity(
                backendUrlServer + "/api/accounts?login=" + login,
                AccountViewModel.class);
        System.out.println(acc);
        return acc.getBody();
    }

    @Override
    public AccountViewModel getAccountById(Integer id) {
        return new RestTemplate().getForObject(
                backendUrlServer + "/api/accounts/" + id,
                AccountViewModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AccountViewModel account = getAccountByLogin(login);
        System.out.println("Account info:" + "[login=" + account.getLogin() + "], [password=" + account.getPassword() +
                "], [role=" + account.getRole() + "]");

        if (account == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(account.getLogin(),
                account.getPassword(),
                getAuthority(account));
    }

    private Set<GrantedAuthority> getAuthority(AccountViewModel account) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));
        return authorities;
    }
}
