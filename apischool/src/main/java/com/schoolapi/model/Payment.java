package com.schoolapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_table")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feeId;

    @Column(name = "adm_no")
    private Integer admNo;

    @Column(name = "fee_name")
    private String feeName;

    @Column(name = "fee_price")
    private Integer feePrice;

    @Column(name = "payment_timestamp")
    private LocalDateTime paymentTimestamp;

    @Column(name = "payment_status")
    private String paymentStatus;

    // Getters and setters
}
