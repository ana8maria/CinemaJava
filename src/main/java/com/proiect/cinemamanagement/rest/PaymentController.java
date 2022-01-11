package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.entity.PaymentEntity;
import com.proiect.cinemamanagement.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cm/api/v1/payments")
public class PaymentController {

    //create object log (type logger) to show info about requests
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    public PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<PaymentEntity> createPayment (@Valid @RequestBody(required = false) CardEntity card, @Valid @RequestBody Ticket ticket)
    {

        PaymentEntity savedPaymentEntitty = paymentService.processPostPayement(card, ticket);

        return new ResponseEntity<>(savedPaymentEntitty, HttpStatus.CREATED);
    }
}
