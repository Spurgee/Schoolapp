package com.schoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.schoolapi.model.Activity;
import com.schoolapi.repository.ActivityRepository;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivityById(int id) {
        return activityRepository.findById(id).orElse(null);
    }

    public List<Activity> getActivitiesForStudent(int admissionNumber) {
        return activityRepository.findByAdmNo(admissionNumber);
    }

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public void deleteActivity(int id) {
        activityRepository.deleteById(id);
    }
}
