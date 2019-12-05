package fapi.service;

import fapi.models.GroupViewModel;
import fapi.models.RestPageImpl;

import javax.servlet.http.HttpServletRequest;

public interface GroupDataService {

    GroupViewModel getGroupById(Integer id);

    GroupViewModel saveGroup(GroupViewModel groupViewModel);

    void deleteGroup(Integer id);

    RestPageImpl<GroupViewModel> getPage(HttpServletRequest request);
}
