package com.example.backend.model.DAO;

import com.example.backend.model.Invite;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
 import org.jdbi.v3.sqlobject.customizer.BindBean;
 import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterConstructorMapper(Invite.class)
public interface InviteDAO {

    @SqlUpdate("""
            INSERT INTO invite (email, name, roleId, status, expiresAt, createdAt)\s
            values (:email, :name, :roleId, :status, :expiresAt, :createdAt)
            """)
    Integer addInvite(@BindBean Invite invite);

}
