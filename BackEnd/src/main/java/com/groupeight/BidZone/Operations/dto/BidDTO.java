package com.groupeight.BidZone.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidDTO {
    private int id;
    private int listingId;
    private int userId;
    private Date timestamp;
    private float price;
}
