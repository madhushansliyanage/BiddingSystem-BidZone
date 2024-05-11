package com.groupeight.BidZone.Operations.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private int id;

    private int listing_Id;

    private int user_Id;

    private int bid_Id;

    private LocalDateTime paid_time;

    private String payment_type;

    private float price;
}
