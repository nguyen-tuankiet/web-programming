package com.example.backend.model.DAO;

import com.example.backend.model.UserRole;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterConstructorMapper(UserRole.class)
public interface UserRoleDAO {

    @SqlUpdate(value = """
            INSERT INTO user_role(userId, roleId)
            VALUES (:userId, :roleId)
            """)
    Integer addUserRole(@Bind("userId") Integer userId, @Bind("roleId") Integer roleId);


    @SqlUpdate(value = """
            UPDATE user_role
            set roleId = :roleId
            where userId= :userid;
            """)
    Boolean updateUserRole(@Bind("userId") Integer userId, @Bind("roleId") Integer roleId);
}
