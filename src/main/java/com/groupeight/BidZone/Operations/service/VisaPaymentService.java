package com.groupeight.BidZone.Operations.service;


import com.groupeight.BidZone.Operations.repo.CardTypeStrategy;
import org.springframework.stereotype.Service;

@Service("Visa")
public class VisaPaymentService implements CardTypeStrategy {

    @Override
    public String getCardType() {
        return "Visa";
    }
}