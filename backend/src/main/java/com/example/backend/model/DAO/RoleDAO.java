package com.example.backend.model.DAO;

import com.example.backend.model.Role;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Role.class)
public interface RoleDAO {
    @SqlQuery(value = """
            SELECT *
            from role
            where isActive= 1
            """)
    List<Role> getRoles();
}
