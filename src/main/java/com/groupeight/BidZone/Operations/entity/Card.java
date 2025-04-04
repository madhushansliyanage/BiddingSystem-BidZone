package com.groupeight.BidZone.Operations.entity;

public class Card {

    private String cardNumber;
    private String expiryDate;
    private String cvv;

    @Override
    public String toString() {
        return "CardDetails{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
