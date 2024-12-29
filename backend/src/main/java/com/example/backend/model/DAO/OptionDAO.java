package com.example.backend.model.DAO;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface OptionDAO {

    @SqlUpdate("INSERT INTO options (productId, price, stock) VALUES (:productId, :price, :stock)")
    int addOptionToProduct(@Bind("productId") Integer productId, @Bind("price") Integer price, @Bind("stock") Integer stock);
}
