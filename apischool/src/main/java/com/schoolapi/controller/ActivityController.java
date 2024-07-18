package com.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.schoolapi.model.Activity;
import com.schoolapi.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable int id) {
        return activityService.getActivityById(id);
    }

    @GetMapping("/student/{admissionNumber}")
    public List<Activity> getActivitiesForStudent(@PathVariable int admissionNumber) {
        return activityService.getActivitiesForStudent(admissionNumber);
    }

    @PostMapping
    public Activity saveActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable int id) {
        activityService.deleteActivity(id);
    }
}
