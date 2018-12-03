package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.GroupViewModel;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GroupDataService {

    GroupViewModel getGroupById(Integer id);

    GroupViewModel saveGroup(GroupViewModel groupViewModel);

    void deleteGroup(Integer id);

    RestPageImpl<GroupViewModel> getPage(HttpServletRequest request);
}
