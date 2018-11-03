package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.GroupInfo;

import java.util.Optional;

public interface GroupInfoService {
    GroupInfo saveGroupInfo(GroupInfo info);

    Optional<GroupInfo> getGroupInfoById(Integer id);

    Iterable<GroupInfo> getAllGroupInfo();

    void deleteGroupInfo(Integer id);
}
