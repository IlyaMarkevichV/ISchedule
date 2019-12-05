package fapi.controller;

import fapi.models.DayViewModel;
import fapi.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ba-days")
public class DayController {

    private DayDataService service;

    @Autowired
    public DayController(DayDataService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<DayViewModel>> getAllDays(){
        return ResponseEntity.ok(service.getAllDays());
    }
}
