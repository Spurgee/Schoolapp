package com.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.schoolapi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
