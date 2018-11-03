package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.GroupInfo;
import com.netcracker.edu.backend.repository.GroupInfoRepository;
import com.netcracker.edu.backend.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {

    private GroupInfoRepository repository;

    @Autowired
    public GroupInfoServiceImpl(GroupInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public GroupInfo saveGroupInfo(GroupInfo info) {
        return repository.save(info);
    }

    @Override
    public Optional<GroupInfo> getGroupInfoById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<GroupInfo> getAllGroupInfo() {
        return repository.findAll();
    }

    @Override
    public void deleteGroupInfo(Integer id) {
        repository.deleteById(id);
    }
}
