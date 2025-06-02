package com.example.backend.model.DAO;

import com.example.backend.model.Member;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(Member.class)
public interface MemberDAO {

    @SqlQuery("""
        SELECT u.id, u.fullName, u.email, u.role, u.status, i.url AS avatarUrl
        FROM user u
        LEFT JOIN image i ON u.avatarId = i.id
    """)
    List<Member> getAll();



    @SqlUpdate("UPDATE user SET status = :status WHERE id = :id")
    void updateStatus(@Bind("id") int id, @Bind("status") String status);

    @SqlUpdate("UPDATE user SET role = :role WHERE id = :id")
    void updateRole(@Bind("id") int id, @Bind("role") String role);
}
