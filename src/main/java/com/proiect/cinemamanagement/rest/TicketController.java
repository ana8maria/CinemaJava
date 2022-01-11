package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.dao.Ticket;
import com.proiect.cinemamanagement.entity.EmployeeEntity;
import com.proiect.cinemamanagement.entity.MovieEntity;
import com.proiect.cinemamanagement.entity.TicketEntity;
import com.proiect.cinemamanagement.service.EmployeeService;
import com.proiect.cinemamanagement.service.MovieService;
import com.proiect.cinemamanagement.service.RoomService;
import com.proiect.cinemamanagement.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;


@RestController
@RequestMapping("/cm/api/v1/ticket")
public class TicketController {
    //create object log (type logger) to show info about requests
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);
    public TicketService ticketService;
    public MovieService movieService;
    public RoomService roomService;
    public EmployeeService employeeService;

    public TicketController(TicketService ticketService, RoomService roomService, MovieService movieService, EmployeeService employeeService) {
        this.ticketService = ticketService;
        this.roomService = roomService;
        this.movieService=movieService;
        this.employeeService=employeeService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<TicketEntity> createTicket(@Valid @RequestBody Ticket ticket){
        Iterable<TicketEntity> ticketEntityIterable;
        ticketEntityIterable=ticketService.processGetTickets();
        if(ticket.getSeatsNumber()!=null && ticket.getRow()!=null) {
            for (TicketEntity t : ticketEntityIterable) {
                if (t.getRow() == ticket.getRow() && t.getSeatsNumber() == ticket.getSeatsNumber()) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "This place is reserved"
                    );
                }
            }
        }
        TicketEntity ticketEntity=ticketService.processPostTickets(ticket);
        return new ResponseEntity<>(ticketEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TicketEntity> getTicket(@PathVariable Integer id) {
        log.info("Received request to get ticket with id: {}", id);
        TicketEntity returnedTicketEnitty = ticketService.processFindById(id);
        log.info("Created returnedTicketEnitty with id: {}", id);
        if (returnedTicketEnitty == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Ticket with campaign id: " + id + " not found in the database");
        }
        return new ResponseEntity<>(returnedTicketEnitty, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/booked-seats")
    public ResponseEntity<String> getBookedSeats(@RequestParam String movieName, @RequestParam String dateAndTime) throws JSONException {

        Iterable<TicketEntity> ticketEntityIterable;
        ticketEntityIterable=ticketService.processFindByMovieNameContaining(movieName, dateAndTime);
        //find room by roomName
        String roomName=null;

        List<Integer> bookedSeats=new ArrayList();
        List<Integer> bookedRows=new ArrayList();

        for (TicketEntity t:ticketEntityIterable) {
            if(t.getSeatsNumber()!=null && t.getRow()!=null) {
                bookedSeats.add(t.getSeatsNumber());
                bookedRows.add(t.getRow());
            }
        }


        JSONObject jsonObject=new JSONObject();

        jsonObject.put("bookedRows", bookedRows);
        jsonObject.put("bookedSeats", bookedSeats);


        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<TicketEntity> editTicket(@PathVariable Integer id, @RequestBody Ticket ticket){
        TicketEntity savedTicketEntity = ticketService.processPutTicket(id,ticket);
        if (savedTicketEntity == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Ticket with id: " + id + " not found in the database");
        }

        return new ResponseEntity<>(savedTicketEntity, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<Iterable<TicketEntity>> getTickets(){
        Iterable<TicketEntity> ticketEntityIterable;
        ticketEntityIterable=ticketService.processGetTickets();

        return new ResponseEntity<>(ticketEntityIterable, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/report")
    public ResponseEntity<String> getReport(@RequestParam String startDate , @RequestParam String endDate) throws ParseException, JSONException {

        Iterable<TicketEntity> ticketEntityIterable;
        ticketEntityIterable=ticketService.findByDateAndTimeInterval(startDate,endDate);

        Float profit=0.0f;
        Iterable<MovieEntity> movieEntityIterable=null;
        Iterable<EmployeeEntity> employeeEntityIterable;

        Float collections=0.0f;
        Float costs=0.0f;

//am adunat pretul fiecarui bilet
        for (TicketEntity t:ticketEntityIterable) {
            if(t.getPrice()!=null && t.getMovieName()!=null) {
                profit += t.getPrice();
                movieEntityIterable = movieService.processFindByNameContaining(t.getMovieName());
            }
        }
    //am stocat incasarile
        collections=profit;

        //am scazut pretul filmului
        Float filmCost= new Float(0);

        if(movieEntityIterable!=null) {
            for (MovieEntity m : movieEntityIterable) {
                filmCost = m.getCost();
            }
        }
        costs+=filmCost;
        profit-=filmCost;
        //am scazut salariile angajatilor

        employeeEntityIterable=employeeService.processGetEmployeesWithoutPagination();
        for(EmployeeEntity e:employeeEntityIterable){
            costs+=e.getSalary();
            profit-=e.getSalary();
        }



        JSONObject jsonObject=new JSONObject();

        jsonObject.put("profit", profit);
        jsonObject.put("collections", collections);
        jsonObject.put("costs", costs);


        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
    }



}
