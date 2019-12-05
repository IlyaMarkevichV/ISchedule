package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Day;
import com.netcracker.edu.backend.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/days")
public class DayController {

    private DayService service;

    @Autowired
    public DayController(DayService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Day> getAllDays() {
        return service.getAllDays();
    }
}
