package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.dao.Card;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cm/api/v1/cards")
public class CardController {

    public CardService cardService;
    private static final Logger log = LoggerFactory.getLogger(CardController.class);

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<CardEntity> createCard(@Valid @RequestBody Card card){

        log.info("Received request to create players: {}", card);
        CardEntity savedCardEntity=cardService.processPostCards(card);

        return new ResponseEntity<>(savedCardEntity, HttpStatus.CREATED);
    }



}
