package com.example.backend.model.DAO;

import com.example.backend.model.Card;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterConstructorMapper(Card.class)
public interface CardDAO {

    @SqlQuery(value = "SELECT *\n" +
            "FROM card\n" +
            "WHERE userId = :userId;")
    List<Card> getCardByUserId(@Bind("userId") Integer userId);

}
