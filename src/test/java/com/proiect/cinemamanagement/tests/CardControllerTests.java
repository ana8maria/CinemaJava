package com.proiect.cinemamanagement.tests;

import com.proiect.cinemamanagement.dao.Card;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.service.CardService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CardService cardService;

    @Test
    public void testPostCard() throws Exception {

        Assert.assertNotNull(cardService);
        ObjectMapper mapper = new ObjectMapper();

        Card card= new Card("firstName", "lastName", "12-02-2021",123);
        CardEntity convertedCardEntity = new CardEntity(card);

        given(this.cardService.processPostCards(card)).willReturn(convertedCardEntity);

        this.mvc.perform(post("/cm/api/v1/cards").content(mapper.writeValueAsString(card))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(convertedCardEntity)));

    }
}
