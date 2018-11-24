package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.RoleViewModel;
import com.netcracker.edu.fapi.service.RoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/ba-roles")
public class RoleDataController {

    private RoleDataService service;

    @Autowired
    public RoleDataController(RoleDataService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<RoleViewModel>> getAllRoles() {
        List<RoleViewModel> roles = service.getAllRoles();
        System.out.println();
        return roles == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(roles);
    }
}
