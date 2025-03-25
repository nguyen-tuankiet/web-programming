package com.example.backend.model.DAO;

import com.example.backend.model.Logs;
 import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterConstructorMapper(Logs.class)
public interface LogsDAO {

    @SqlUpdate("INSERT INTO logs (type, createdAt, oldData, newData) " +
            "VALUES (:type, :createdAt, :oldData, :newData)")
    @GetGeneratedKeys("id")
    int insert(@Bind("type") String type,
                @Bind("createdAt") Long createdAt,
                @Bind("oldData") String oldData,
                @Bind("newData") String newData
                );

}
