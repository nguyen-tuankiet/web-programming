package com.example.backend.model.DAO;

import com.example.backend.model.User;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterConstructorMapper(User.class)
public interface UserDao {

    @SqlQuery("SELECT * FROM user")
    List<User> getAllUsers();

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    User getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM user WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO user (fullName, displayName, email, password, role) " +
            "VALUES (:fullName, :displayName, :email, :password, 'USER')")
    @GetGeneratedKeys("id")
    String createUser(@Bind("fullName") String fullName,
                      @Bind("displayName") String displayName,
                      @Bind("email") String email,
                      @Bind("password") String password);


    @SqlUpdate("UPDATE user SET fullname = :fullname, email = :email, password = :password WHERE id = :id")
    void updateUser(@Bind("id") Integer id,
                    @Bind("fullname") String fullname,
                    @Bind("email") String email,
                    @Bind("password") String password);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void deleteUser(@Bind("id") Integer id);
}
