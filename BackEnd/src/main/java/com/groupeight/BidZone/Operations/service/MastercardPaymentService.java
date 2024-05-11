package com.groupeight.BidZone.Operations.service;

import com.groupeight.BidZone.Operations.repo.CardTypeStrategy;
import org.springframework.stereotype.Service;

@Service("Mastercard")
public class MastercardPaymentService implements CardTypeStrategy {

    @Override
    public String getCardType() {
        return "Mastercard";
    }
}
