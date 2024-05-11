package com.groupeight.BidZone.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidDTO {
    private int id;
    private int listingId;
    private int userId;
    private LocalDateTime timestamp;
    private float price;
    private String status;
}
