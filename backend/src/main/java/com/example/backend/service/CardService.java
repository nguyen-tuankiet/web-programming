package com.example.backend.service;

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

}
