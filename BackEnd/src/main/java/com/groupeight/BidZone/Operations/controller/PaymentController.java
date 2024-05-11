package com.groupeight.BidZone.Operations.controller;

import com.groupeight.BidZone.Operations.dto.PaymentDTO;
import com.groupeight.BidZone.Operations.entity.Payment;
import com.groupeight.BidZone.Operations.repo.CardTypeStrategy;
import com.groupeight.BidZone.Operations.repo.PaymentRepo;
import com.groupeight.BidZone.Operations.service.CardTypeSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
@CrossOrigin
public class PaymentController {

    private final PaymentRepo paymentRepo;

    @Autowired
    public PaymentController(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @PostMapping("/addpayment")
    public ResponseEntity<String> processPayment(@RequestBody PaymentDTO paymentDTO) {
        // Convert PaymentDTO to PaymentEntity (assuming you have an entity mapping)


        System.out.println(paymentDTO);
        Payment payment = mapDtoToEntity(paymentDTO);

        // Save paymentEntity to the database using the repository
        paymentRepo.save(payment);

        return ResponseEntity.ok("Payment processed successfully!");
    }


    // Utility method to map PaymentDTO to PaymentEntity (customize as per your entity structure)
    private Payment mapDtoToEntity(PaymentDTO paymentDTO) {
        String Type = paymentDTO.getPayment_type();

        //Get card type using strategy design pattern
        String CardType = new CardTypeSelectionService().cardTypeSelection(Type);


        Payment payment = new Payment();
        // Map properties from paymentDTO to paymentEntity
        payment.setListing_Id(paymentDTO.getListing_Id());
        payment.setUser_Id(paymentDTO.getUser_Id());
        payment.setBid_Id(paymentDTO.getBid_Id());
        payment.setPaid_time(paymentDTO.getPaid_time());
        payment.setPayment_type(CardType);
        payment.setPrice(paymentDTO.getPrice());
        // Set other properties as needed
        return payment;
    }
}
