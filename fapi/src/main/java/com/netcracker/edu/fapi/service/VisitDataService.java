package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.VisitViewModel;

import java.util.List;

public interface VisitDataService {
    List<VisitViewModel> getAllVisits();

    VisitViewModel getVisitById(Integer id);

    VisitViewModel saveVisit(VisitViewModel visit);

    void deleteVisit(Integer id);
}
