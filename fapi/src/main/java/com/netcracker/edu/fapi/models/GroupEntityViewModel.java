package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupEntityViewModel {

    private int groupId;

   // private FacultyEntity facultyEntity;

    private int grade;

    private Date date;
}
