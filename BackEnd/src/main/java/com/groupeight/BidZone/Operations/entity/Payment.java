package com.groupeight.BidZone.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int listing_Id;

    @Column(nullable = false)
    private int user_Id;

    @Column(nullable = false)
    private int bid_Id;

    @Column(nullable = false)
    private LocalDateTime paid_time;

    @Column(nullable = false)
    private String payment_type;

    @Column(nullable = false, columnDefinition = "float default 0")
    private float price;

}
