package com.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.schoolapi.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
