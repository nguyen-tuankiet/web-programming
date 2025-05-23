package com.example.backend.model.DAO;

import com.example.backend.contant.Status;
import com.example.backend.model.Invite;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterConstructorMapper(Invite.class)
public interface InviteDAO {

    @SqlUpdate("""
            INSERT INTO invite (email, name, roleId, status, expiresAt, createdAt)\s
            values (:email, :name, :roleId, :status, :expiresAt, :createdAt)
            """)
    @GetGeneratedKeys
    Integer addInvite(@BindBean Invite invite);



    @SqlQuery("""
            SELECT *
            from invite
            where id = :id and email = :email
            """)
    Invite getInviteByIdAndEmail(@Bind("id") Integer id, @Bind("email") String email);


    @SqlUpdate(value = """
            UPDATE invite
            set status = :status
            WHERE id = :id
            """)
    boolean updateInviteStatus(@Bind("id") Integer id, @Bind("status") Status status);





}
