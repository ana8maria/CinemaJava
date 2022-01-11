package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.TicketEntity;
import com.proiect.cinemamanagement.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);
    private TicketRepository ticketRepository;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketEntity processPostTickets(Ticket ticket){
        TicketEntity ticketEntity = new TicketEntity(ticket);

        return ticketRepository.save(ticketEntity);
    }

    public Iterable<TicketEntity> processGetTickets(){return ticketRepository.findAll();}

    public Iterable<TicketEntity> processFindByMovieNameContaining(String movieName, String dateAndTime){
        return ticketRepository.findByMovieNameAndDateAndTimeContaining(movieName, dateAndTime);
    }
    public Iterable<TicketEntity> findByDateAndTimeInterval(String startDate, String endDate) throws ParseException {
        Date dateStart = formatter.parse(startDate);
        Date dateEnd = formatter.parse(endDate);

        return ticketRepository.findByDateAndTimeInterval(dateStart, dateEnd);
    }

    public Integer findTicketNoByMovieName(String movieName){
        return ticketRepository.findNoOfTIcketsByMovieName(movieName);
    }

    public TicketEntity processFindById(Integer id){
        return ticketRepository.findById(id).orElse(null);
    }

    public TicketEntity processPutTicket(Integer id, Ticket ticket){
        TicketEntity ticketEntity=ticketRepository.findById(id).orElse(null);
        if(ticketEntity==null){
            return null;
        }
        ticketEntity.setSeatsNumber(ticket.getSeatsNumber());
        ticketEntity.setDateAndTime(ticket.getDateAndTime());
        ticketEntity.setMovieName(ticket.getMovieName());
        ticketEntity.setRoomName(ticket.getRoomName());
        ticketEntity.setPrice(ticket.getPrice());
        ticketEntity.setRow(ticket.getRow());
        return ticketRepository.save(ticketEntity);
    }


}
