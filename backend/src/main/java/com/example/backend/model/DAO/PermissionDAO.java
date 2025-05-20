package com.example.backend.model.DAO;

import com.example.backend.model.Permission;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Permission.class)
public interface PermissionDAO {

    @SqlQuery(value = """
        select * from permission
""")
    public List<Permission> getAllPermissions();




}
