package com.example.backend.model.DAO;

import com.example.backend.model.Card;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterConstructorMapper(Card.class)
public interface CardDAO {

    @SqlQuery(value = "SELECT *\n" +
            "FROM card\n" +
            "WHERE userId = :id;")
    Card getCardById(@Bind("id") Integer id);

}
