package com.proiect.cinemamanagement.tests;

import com.proiect.cinemamanagement.service.PaymentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PaymentControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentService paymentService;

//    @Test
//    public void testPostPayment() throws Exception {
//
//        Assert.assertNotNull(paymentService);
//        ObjectMapper mapper = new ObjectMapper();
//
//        Card card= new Card("firstName", "lastName", "12-02-2021",123);
//        Payment payment= new Payment(12.3f, card);
//        PaymentEntity convertedPaymentEntity = new PaymentEntity(payment);
//
//        Ticket ticket=new Ticket("movieName", "12-12-2020T21:20","roomName", 2, 3, 3.4f,);
//        TicketEntity convertedTicketEntity = new TicketEntity(ticket);
//
//        given(this.paymentService.processPostPayement(Class<card>, ticket)).willReturn(convertedPaymentEntity);
//
//        this.mvc.perform(post("/cm/api/v1/payments").content(mapper.writeValueAsString(payment))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(content().string(mapper.writeValueAsString(convertedPaymentEntity)));
//
//    }
}
