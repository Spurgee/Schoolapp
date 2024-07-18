package com.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.schoolapi.model.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
