package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.entity.PaymentEntity;
import com.proiect.cinemamanagement.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    //post paiment
    public PaymentEntity processPostPayement(CardEntity card, Ticket ticket){

        PaymentEntity paymentEntity = new PaymentEntity(ticket.getPrice(), card);
//        paymentEntity.setCard(card) ;
//        paymentEntity.setAmount(ticket.getPrice());
        return paymentRepository.save(paymentEntity);
    }

}
