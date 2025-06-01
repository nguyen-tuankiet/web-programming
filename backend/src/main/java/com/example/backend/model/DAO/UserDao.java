package com.example.backend.model.DAO;

import com.example.backend.model.User;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RegisterConstructorMapper(User.class)
public interface UserDao {

//    @SqlQuery("SELECT * FROM user")
//    List<User> getAllUsers();

    @SqlQuery("""
    SELECT u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone,
           i.url AS avatarUrl,
           u.status, u.confirmationToken, u.role, u.password, u.salt, u.facebookId
    FROM user u
    LEFT JOIN image i ON u.avatarId = i.id
""")
    List<User> getAllUsers();


    @SqlQuery(value = "select u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone,\n" +
            "        i.url as avatarUrl, u.status, u.confirmationToken, u.role, u.password, u.salt, u.facebookId\n" +
            "from user as u\n" +
            "    left join image as i on u.avatarId = i.id\n" +
            "where u.id  = :id")
    User getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM user WHERE email = :email")
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM user WHERE confirmationToken = :token")
    User getUserByConfirmationToken(@Bind("token") String token);

    @SqlQuery("""
    SELECT u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone,
           i.url AS avatarUrl,
           u.status, u.confirmationToken, u.role, u.password, u.salt, u.facebookId
    FROM user u
    LEFT JOIN image i ON u.avatarId = i.id
    WHERE (:keyword IS NULL OR :keyword = '' OR LOWER(u.fullName) LIKE CONCAT('%', LOWER(:keyword), '%'))
""")
    List<User> getUsersByKeyword(@Bind("keyword") String keyword);


    @SqlUpdate("INSERT INTO user (fullName, displayName, email, password, role, salt, status, confirmationToken, facebookId) " +
            "VALUES (:fullName, :displayName, :email, :password, 'USER', :salt, 'PENDING', :confirmationToken, :facebookId)")
    @GetGeneratedKeys("id")
    String createUser(@Bind("fullName") String fullName,
                      @Bind("displayName") String displayName,
                      @Bind("email") String email,
                      @Bind("password") String password,
                      @Bind("salt") String salt,
                      @Bind("confirmationToken") String confirmationToken,
                      @Bind("facebookId") String facebookId);

    @SqlUpdate("UPDATE user SET status = :status WHERE id = :id")
    void updateUserStatus(@Bind("id") Integer id, @Bind("status") String status);

    @SqlUpdate("UPDATE user SET status = :status WHERE confirmationToken = :token")
    void updateUserStatusByToken(@Bind("token") String token, @Bind("status") String status);

    @SqlUpdate("UPDATE user SET fullname = :fullname, email = :email, password = :password WHERE id = :id")
    void updateUser(@Bind("id") Integer id,
                    @Bind("fullname") String fullname,
                    @Bind("email") String email,
                    @Bind("password") String password);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void deleteUser(@Bind("id") Integer id);

    @SqlUpdate("UPDATE user SET password = :password, salt = :salt WHERE id = :id")
    int updatePassword(@Bind("id") Integer id, @Bind("password") String password, @Bind("salt") String salt);

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    User getPasswordByUserId(@Bind("id") Integer userId);

    @SqlQuery("SELECT url FROM image WHERE id = :avatarId")
    String getAvatarUrlById(@Bind("avatarId") Integer avatarId);

    @SqlUpdate(value ="UPDATE user\n" +
            "SET avatarId = :avatarId " +
            "where id = :userId")
    Boolean updateAvatar(@Bind("userId") Integer userId, @Bind("avatarId") Integer avatarId);

    @SqlUpdate(value = "UPDATE user\n" +
            "SET\n" +
            "    fullName = :fullName ,\n" +
            "    displayName = :displayName,\n" +
            "    birth = :birth, " +
            "    gender = :gender,\n" +
            "    phone = :phone, " +
            "where id = :userId")
    Boolean updateUser(
            @Bind("userId") Integer userId,
            @Bind("fullName") String fullName,
            @Bind("displayName") String displayName,
            @Bind("birth") LocalDate birth,
            @Bind("gender") String gender,
            @Bind("phone") String phone
    );
}
