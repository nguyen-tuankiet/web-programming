package com.example.backend.model.DAO;

import com.example.backend.model.Mapper.UserWithRoleMapper;
import com.example.backend.model.User;
import com.example.backend.model.Role;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDate;
import java.util.List;

@RegisterConstructorMapper(User.class)
@RegisterConstructorMapper(Role.class)
public interface UserDao {


    @SqlQuery("SELECT u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone, " +
            "u.password, u.avatarId, u.salt, i.url as avatarUrl, u.status, u.confirmationToken, u.facebookId, " +
            "r.id as 'role.id', r.roleType as 'role.roleType', r.name as 'role.name', " +
            "r.description as 'role.description', r.isActive as 'role.isActive' " +
            "FROM user u " +
            "LEFT JOIN image i ON u.avatarId = i.id " +
            "LEFT JOIN role r ON u.roleId = r.id")
    List<User> getAllUsers();

    @SqlQuery(value = "select u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone,\n" +
            "        i.url as avatarUrl, u.status, u.confirmationToken, u.password, u.salt, u.facebookId,\n" +
            "        r.id as role_id, r.roleType as role_roleType, r.name as role_name, r.description as role_description, r.isActive as role_isActive\n" +
            "from user as u\n" +
            "    left join image as i on u.avatarId = i.id\n" +
            "    left join role as r on u.roleId = r.id\n" +
            "where u.id  = :id")
    User getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone, " +
            "u.password, u.salt, u.avatarId, u.status, u.confirmationToken, u.facebookId, " +
            "i.url as avatarUrl, " +
            "r.id as role_id, r.roleType as role_roleType, r.name as role_name, " +
            "r.description as role_description, r.isActive as role_isActive " +
            "FROM user as u " +
            "LEFT JOIN image as i ON u.avatarId = i.id " +
            "LEFT JOIN role as r ON u.roleId = r.id " +
            "WHERE u.email = :email")
    @RegisterRowMapper(UserWithRoleMapper.class)
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery(value = "SELECT u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone,\n" +
            "        i.url as avatarUrl, u.status, u.confirmationToken, u.password, u.salt, u.facebookId,\n" +
            "        r.id as role_id, r.roleType as role_roleType, r.name as role_name, r.description as role_description, r.isActive as role_isActive\n" +
            "FROM user as u\n" +
            "    left join image as i on u.avatarId = i.id\n" +
            "    left join role as r on u.roleId = r.id\n" +
            "WHERE u.confirmationToken = :token")
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

    @SqlQuery(value = "SELECT u.id, u.fullName, u.displayName, u.birth, u.gender, u.email, u.phone,\n" +
            "        i.url as avatarUrl, u.status, u.confirmationToken, u.password, u.salt, u.facebookId,\n" +
            "        r.id as role_id, r.roleType as role_roleType, r.name as role_name, r.description as role_description, r.isActive as role_isActive\n" +
            "FROM user as u\n" +
            "    left join image as i on u.avatarId = i.id\n" +
            "    left join role as r on u.roleId = r.id\n" +
            "WHERE u.id = :id")
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
            "    phone = :phone " +
            "where id = :userId")
    Boolean updateUser(
            @Bind("userId") Integer userId,
            @Bind("fullName") String fullName,
            @Bind("displayName") String displayName,
            @Bind("birth") LocalDate birth,
            @Bind("gender") String gender,
            @Bind("phone") String phone
    );

    // Helper method to get default user role
    @SqlQuery("SELECT id, roleType, name, description, isActive FROM role WHERE roleType = 'USER' LIMIT 1")
    Role getDefaultUserRole();

    // Helper method to get role by name
    @SqlQuery("SELECT id, roleType, name, description, isActive FROM role WHERE name = :name")
    Role getRoleByName(@Bind("name") String name);

    // Helper method to get role by type
    @SqlQuery("SELECT id, roleType, name, description, isActive FROM role WHERE roleType = :roleType")
    Role getRoleByType(@Bind("roleType") String roleType);
}