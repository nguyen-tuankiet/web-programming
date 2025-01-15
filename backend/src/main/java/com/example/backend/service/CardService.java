package com.example.backend.service;

import com.example.backend.Connection.DBConnection;
import com.example.backend.model.Card;
import com.example.backend.model.DAO.CardDAO;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CardService {
    CardDAO cardDAO;

    public CardService(Jdbi jdbi) {
        this.cardDAO = jdbi.onDemand(CardDAO.class);
    }

    public List<Card> getCartByUserId(Integer userId) {
        return cardDAO.getCardByUserId(userId);
    }

    public Card getCardById(Integer cardId) {
        return cardDAO.getCardById(cardId);
    }

    public Boolean addCard(Card card) {
        return cardDAO.addCard(
                card.getUserId(),
                card.getDuration(),
                card.getType(),
                card.getIsDefault(),
                card.getLast4()
        );
    }


    public static void main(String[] args) {
        CardService cardService = new CardService(DBConnection.getJdbi());
        System.out.println(cardService.getCartByUserId(1));
//        System.out.println(cardService.getCardById(1));
//        System.out.println(cardService.getCardById(1));
//        System.out.println(cardService.getCardById(1));
    }

}
