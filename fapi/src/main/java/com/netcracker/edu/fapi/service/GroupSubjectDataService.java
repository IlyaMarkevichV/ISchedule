package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.GroupSubjectViewModel;

import java.util.List;

public interface GroupSubjectDataService {
    List<GroupSubjectViewModel> getAllGroupSubjects();

    GroupSubjectViewModel getGroupSubjectById(Integer id);

    GroupSubjectViewModel saveGroupSubject(GroupSubjectViewModel subjectViewModel);

    void deleteGroupSubject(Integer id);
}
