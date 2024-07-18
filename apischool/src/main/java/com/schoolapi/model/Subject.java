package com.schoolapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subject_table")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_marks")
    private Integer subjectMarks;

    @Column(name = "adm_no")
    private Integer admNo;

    // Getters and setters
}
