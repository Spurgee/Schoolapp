package com.schoolapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "results_table")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    @Column(name = "adm_no")
    private Integer admNo;

    @Column(name = "subject_id")
    private Integer subjectId;

    // Getters and setters
}
