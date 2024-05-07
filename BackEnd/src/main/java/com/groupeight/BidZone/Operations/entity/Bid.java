package com.groupeight.BidZone.Operations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable=false)
    private int listingId;

    @JoinColumn(nullable=false)
    private int userId;

    @Column(nullable = false)
    private Date timestamp;

    @Column(nullable = false)
    private float price;
}
