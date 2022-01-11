package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.dao.Card;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CardService {
    private CardRepository cardRepository;
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardEntity processPostCards(Card card){
        CardEntity cardEntity= new CardEntity(card);

        //verificam ca CVV-ul sa fie pozitiv
        if(card.getCVV()<0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "CVV cannot be negative"
            );
        }

        //verificam ca CVV-ul sa aiba exact trei cifre
        if(card.getCVV()<100 || card.getCVV()>999){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "CVV must contain 3 caracters"
            );
        }

//        DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        //luam data curenta
//        LocalDateTime currentUtilDate = LocalDateTime.now();
//        currentUtilDate= LocalDateTime.parse(currentUtilDate.toString(), df);
//        log.info("currentDate", currentUtilDate);
//        //parsam data care este sub forma de string pentru a putea compara cu data curenta
//        LocalDateTime  date = LocalDateTime .parse(card.getExpirationDate(), df);
//        //verificarea ca nu e card expirat
//
//        log.info("myDate", date);
//        log.info("comparation", date.isBefore(currentUtilDate));
//        if(date.isBefore(currentUtilDate)){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Card expired"
//            );
//        }
        return cardRepository.save(cardEntity);
    }


}
