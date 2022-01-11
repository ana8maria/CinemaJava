package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.TicketEntity;
import com.proiect.cinemamanagement.repository.TicketRepository;
import com.proiect.cinemamanagement.service.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {
    @Mock
    private TicketRepository ticketRepository;
    @InjectMocks
    private TicketService ticketService;

    private static Ticket ticket;

    private static List<Ticket> tickets;


    @BeforeAll
    public static void setup(){
        ticket=new Ticket("Film", "20-02-2021", "room", 2,2, 3f);
        tickets = new ArrayList<Ticket>();
        tickets.add(ticket);
        Ticket ticket2=new Ticket("Film", "20-02-2021", "room", 2,2, 3f);
        tickets.add(ticket2);
    }

    @Test
    @DisplayName("Test save ticket")
    public void testSaveTicket(){
        TicketEntity ticketEntity=new TicketEntity(ticket);
        doReturn(ticketEntity).when(ticketRepository).save(ticketEntity);
        ticketService.processPostTickets(ticket);
    }

    @Test
    @DisplayName("Test get tickets")
    public void testGetAllTickets(){
        doReturn(tickets).when(ticketRepository).findAll();
        ticketService.processGetTickets();
    }

    @Test
    @DisplayName("Test get ticket")
    public void testGetTicket(){
        TicketEntity ticketEntity=new TicketEntity(ticket);
        doReturn(tickets).when(ticketRepository).findByMovieNameAndDateAndTimeContaining(ticketEntity.getMovieName(), ticketEntity.getDateAndTime());
        ticketService.processFindByMovieNameContaining(ticketEntity.getMovieName(), ticketEntity.getDateAndTime());
    }

    @Test
    @DisplayName("Test put ticket")
    public void testPutTicket(){
        TicketEntity ticketEntity=new TicketEntity(ticket);
//        doReturn(ticketEntity).when(ticketRepository).save(ticketEntity);
        ticketService.processPutTicket(ticketEntity.getId(),ticket);
    }


}
