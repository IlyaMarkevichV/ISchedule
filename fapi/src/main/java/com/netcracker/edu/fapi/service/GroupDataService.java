package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.GroupViewModel;

import java.util.List;

public interface GroupDataService {
    List<GroupViewModel> getAll();

    GroupViewModel getGroupById(Integer id);

    GroupViewModel saveGroup(GroupViewModel groupViewModel);

    void deleteGroup(Integer id);
}
