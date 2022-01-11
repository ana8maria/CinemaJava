package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Card;
import com.proiect.cinemamanagement.dao.Payment;
import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.entity.PaymentEntity;
import com.proiect.cinemamanagement.repository.PaymentRepository;
import com.proiect.cinemamanagement.service.PaymentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentService paymentService;

    private static Payment payment;

    private static List<Payment> payments;

    private static Card card;


    @BeforeAll
    public static void setup(){
        card=new Card("Mihai", "Ioana-Alexandra", "20-03-2021", 123);
        payment=new Payment(4f, card);
        payments = new ArrayList<Payment>();
        payments.add(payment);
        Payment payment2=new Payment(200f, card);
        payments.add(payment2);
    }

    @Test
    @DisplayName("Test save payment")
    public void testSavePayment(){
        PaymentEntity paymentEntity=new PaymentEntity(payment);
        CardEntity cardEntity=new CardEntity(card);
        Ticket ticket= new Ticket("Film", "20-02-2021", "roomName", 2, 4, 4f);
        lenient().when(paymentRepository.save(paymentEntity)).thenReturn(paymentEntity);
        paymentService.processPostPayement(cardEntity, ticket);
    }


}
