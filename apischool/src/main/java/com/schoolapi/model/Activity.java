package com.schoolapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "activities_table")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;

    private String activityName;
    private Integer admNo;
    private Integer activityPrice;

    // Constructors
    public Activity() {}

    public Activity(String activityName, Integer admNo, Integer activityPrice) {
        this.activityName = activityName;
        this.admNo = admNo;
        this.activityPrice = activityPrice;
    }

    // Getters and setters
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getAdmNo() {
        return admNo;
    }

    public void setAdmNo(Integer admNo) {
        this.admNo = admNo;
    }

    public Integer getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Integer activityPrice) {
        this.activityPrice = activityPrice;
    }
}
