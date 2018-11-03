package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.GroupInfoViewModel;

import java.util.List;

public interface GroupInfoDataService {
    List<GroupInfoViewModel> getAllGroupInfo();

    GroupInfoViewModel getGroupInfoById(Integer id);

    GroupInfoViewModel saveGroupInfo(GroupInfoViewModel groupInfof);

    void deleteGroupInfo(Integer id);
}
