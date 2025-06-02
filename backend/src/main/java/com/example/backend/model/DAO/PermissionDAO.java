package com.example.backend.model.DAO;

import com.example.backend.model.Permission;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Permission.class)
public interface PermissionDAO {

    @SqlQuery(value = """
        select * from permission
""")
    public List<Permission> getAllPermissions();

    @SqlQuery("SELECT p.id, p.name, p.type " +
            "FROM permission p " +
            "INNER JOIN role_permission rp ON p.id = rp.permissionId " +
            "WHERE rp.roleId = :roleId")
    List<Permission> getPermissionsByRoleId(@Bind("roleId") Integer roleId);

    @SqlQuery("SELECT * FROM permission WHERE id = :id")
    Permission getPermissionById(Integer id);


}
