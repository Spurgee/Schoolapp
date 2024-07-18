package com.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.schoolapi.model.Activity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByAdmNo(int admNo);
}
