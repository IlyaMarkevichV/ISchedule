package fapi.controller;

import fapi.models.GroupViewModel;
import fapi.service.GroupDataService;
import fapi.models.RestPageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/ba-groups")
public class GroupDataController {

    private GroupDataService groupService;

    @Autowired
    public GroupDataController(GroupDataService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupViewModel> getGroupById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<GroupViewModel> saveGroup(@RequestBody GroupViewModel groupViewModel) {
        if (groupViewModel != null) {
            return ResponseEntity.ok(groupService.saveGroup(groupViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable(name = "id")  Integer id) {
        groupService.deleteGroup(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<RestPageImpl<GroupViewModel>> getPage(HttpServletRequest request) {
        return ResponseEntity.ok(groupService.getPage(request));
    }
}
