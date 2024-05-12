package com.groupeight.BidZone.Operations.repo;

import com.groupeight.BidZone.Operations.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    // Define methods for interacting with payment entities, such as saving payment data
    // Example: Save payment information
    Payment save(Payment payment);

    // Add more methods for CRUD operations if needed

}
