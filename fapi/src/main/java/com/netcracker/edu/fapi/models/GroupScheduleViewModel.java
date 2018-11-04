package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupScheduleViewModel {
    private int id;
    private ScheduleViewModel schedule;
    private GroupViewModel group;

    public GroupScheduleViewModel() {
    }

    public GroupScheduleViewModel(int id, ScheduleViewModel schedule, GroupViewModel group) {
        this.id = id;
        this.schedule = schedule;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduleViewModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleViewModel schedule) {
        this.schedule = schedule;
    }

    public GroupViewModel getGroup() {
        return group;
    }

    public void setGroup(GroupViewModel group) {
        this.group = group;
    }
}
