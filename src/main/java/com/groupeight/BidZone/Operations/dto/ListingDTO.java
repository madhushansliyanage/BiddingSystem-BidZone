package com.groupeight.BidZone.Operations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListingDTO {
    private int id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime ending;
    private float price;
    private String image;
    private int userId;
}
