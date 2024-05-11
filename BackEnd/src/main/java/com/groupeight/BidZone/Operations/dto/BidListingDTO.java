package com.groupeight.BidZone.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidListingDTO {
    private int bidId;
    private int listingId;
    private int userId;
    private String listingName;
    private String listingDescription;
    private String listingCategory;
    private LocalDateTime listingEnding;
    private LocalDateTime bidTimestamp;
    private float bidPrice;
}
