package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Card;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.repository.CardRepository;
import com.proiect.cinemamanagement.service.CardService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class CardServiceTests {
    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private CardService cardService;

    private static Card card;
    private static Card cardWrong;
    private static Card cardWrong2;

    @BeforeAll
    public static void setup(){
        card=new Card("Mihai", "Ioana-Alexandra", "20-03-2021", 123);
        cardWrong=new Card("Mihai", "Ioana-Alexandra", "20-03-2021", 1234);
        cardWrong2=new Card("Mihai", "Ioana-Alexandra", "20-03-2021", -123);
    }

    @Test
    @DisplayName("Test save card")
    public void testSaveCard(){
        CardEntity cardEntity=new CardEntity(card);

        doReturn(cardEntity).when(cardRepository).save(cardEntity);
        cardService.processPostCards(card);
    }

    @Test
    @DisplayName("Test invalid cvv")
    public void testInvalidCVV() {
        CardEntity cardEntity=new CardEntity(card);

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () ->
                cardService.processPostCards(cardWrong));
        assertEquals(  "CVV must contain 3 caracters", responseStatusException.getReason());
    }

    @Test
    @DisplayName("Test negative cvv")
    public void testNegativedCVV() {

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () ->
                cardService.processPostCards(cardWrong2));
        assertEquals(  "CVV cannot be negative", responseStatusException.getReason());
    }
}
