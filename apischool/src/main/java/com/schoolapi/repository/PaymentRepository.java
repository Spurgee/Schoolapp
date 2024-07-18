package com.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.schoolapi.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
