package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.FapiApplication;
import com.netcracker.edu.fapi.models.AccountViewModel;
import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.models.StudentGroupViewModel;
import com.netcracker.edu.fapi.resource.Constants;
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

    final static Logger logger = Logger.getLogger(AccountDataServiceImpl.class);

    @Override
    public AccountViewModel getAccountByLogin(@NotNull String login) {
        ResponseEntity<AccountViewModel> acc = new RestTemplate().getForEntity(
                backendUrlServer + "/api/accounts?login=" + login,
                AccountViewModel.class);
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

        if (account == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(account.getLogin(),
                account.getPassword(),
                getAuthority(account));
    }

    private Set<GrantedAuthority> getAuthority(AccountViewModel account) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getRoleName()));
        return authorities;
    }
}
