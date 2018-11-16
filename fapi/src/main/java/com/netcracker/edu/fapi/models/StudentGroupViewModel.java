package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentGroupViewModel {
    private int id;
    private AccountViewModel account;
    private GroupViewModel group;

    public StudentGroupViewModel() {
    }

    public StudentGroupViewModel(int id, AccountViewModel account, GroupViewModel group) {
        this.id = id;
        this.account = account;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountViewModel getAccount() {
        return account;
    }

    public void setAccount(AccountViewModel account) {
        this.account = account;
    }

    public GroupViewModel getGroup() {
        return group;
    }

    public void setGroup(GroupViewModel group) {
        this.group = group;
    }
}
