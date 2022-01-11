package com.proiect.cinemamanagement.tests;

import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.TicketEntity;
import com.proiect.cinemamanagement.service.TicketService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TicketControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void testPostTicket() throws Exception {

        Assert.assertNotNull(ticketService);
        ObjectMapper mapper = new ObjectMapper();

        Ticket ticket= new Ticket("movieName", "12-12-2020", "roomName",1, 2, 3.4f);
        TicketEntity convertedTicketEntity = new TicketEntity(ticket);

        given(this.ticketService.processPostTickets(ticket)).willReturn(convertedTicketEntity);

        this.mvc.perform(post("/cm/api/v1/ticket").content(mapper.writeValueAsString(ticket))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(convertedTicketEntity)));

    }

    @Test
    public void testGetTickets() throws Exception{
        ArrayList<TicketEntity> list = new ArrayList<>();

        Assert.assertNotNull(ticketService);

        TicketEntity ticket= new TicketEntity(1, "movieName", "12-12-2020", "roomName",1, 2, 3.4f);
        TicketEntity ticket2= new TicketEntity(2, "movieName", "12-12-2020", "roomName",2, 2, 3.77f);
        list.add(ticket);
        list.add(ticket2);
        ObjectMapper mapper = new ObjectMapper();
        given(this.ticketService.processGetTickets()).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/ticket").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void testGetBookedSeats() throws Exception{
        ArrayList<TicketEntity> list = new ArrayList<>();

        Assert.assertNotNull(ticketService);

        TicketEntity ticket= new TicketEntity(1, "movieName", "12-12-2020", "roomName",1, 2, 3.4f);
        TicketEntity ticket2= new TicketEntity(2, "movieName", "12-12-2020", "roomName",2, 2, 3.77f);
        list.add(ticket);
        list.add(ticket2);

        List<Integer> bookedSeats=new ArrayList();
        List<Integer> bookedRows=new ArrayList();

        bookedSeats.add(ticket.getSeatsNumber());
        bookedSeats.add(ticket2.getSeatsNumber());
        bookedRows.add(ticket.getRow());
        bookedRows.add(ticket2.getRow());
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("bookedRows", bookedRows);
        jsonObject.put("bookedSeats", bookedSeats);


        ObjectMapper mapper = new ObjectMapper();
        given(this.ticketService.processFindByMovieNameContaining("movieName", "12-12-2020")).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/ticket/booked-seats?movieName=movieName&dateAndTime=12-12-2020").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(jsonObject.toString())));
    }

    @Test
    public void testPutTicket() throws Exception {

        Assert.assertNotNull(ticketService);

        Ticket ticket =
                new Ticket(
                        "movieName", "12-12-2020", "roomName",1, 2, 3.4f);

        TicketEntity convertedTicket = new TicketEntity(ticket);

        ObjectMapper mapper = new ObjectMapper();

        given(this.ticketService.processPutTicket(2, ticket)).willReturn(convertedTicket);
        this.mvc
                .perform(
                        put("/cm/api/v1/ticket/2")
                                .content(mapper.writeValueAsString(ticket))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(convertedTicket)));
    }

}
