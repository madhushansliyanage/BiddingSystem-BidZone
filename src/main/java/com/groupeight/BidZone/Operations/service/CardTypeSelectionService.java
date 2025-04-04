package com.groupeight.BidZone.Operations.service;

public class CardTypeSelectionService {

    // Method to select the appropriate strategy based on the card number prefix
    public String cardTypeSelection(String type) {

        if (type.equals("MasterCard")) {
            return new MastercardPaymentService().getCardType();
        }
        else if (type.equals("VisaCard")) {
            return new VisaPaymentService().getCardType();
        } else {
            return null;
        }
    }
}
