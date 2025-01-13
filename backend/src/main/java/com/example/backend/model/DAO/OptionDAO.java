package com.example.backend.model.DAO;

import com.example.backend.model.Options;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


@RegisterConstructorMapper(Options.class)
public interface OptionDAO {

    @SqlUpdate("INSERT INTO options (productId, price, stock) VALUES (:productId, :price, :stock)")
    @GetGeneratedKeys
    int createOption(@Bind("productId") Integer productId, @Bind("price") Integer price, @Bind("stock") Integer stock);


    @SqlQuery(value = "select *\n" +
            "from options\n" +
            "where id = :id;")
    Options getOptionById(@Bind("id") Integer id);



    @SqlUpdate(value = "update options\n" +
            "set\n" +
            "    stock = :stock " +
            "where id = :id")
    Boolean updateStock(@Bind("id") Integer id, @Bind("stock") Integer stock);



}