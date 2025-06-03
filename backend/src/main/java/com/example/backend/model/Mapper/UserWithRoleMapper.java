package com.example.backend.model.Mapper;

import com.example.backend.contant.ERole;
import com.example.backend.model.Role;
import com.example.backend.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserWithRoleMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {
        // Tạo đối tượng Role nếu có dữ liệu role
        Role role = null;
        if (rs.getObject("role_id") != null) {
            // Xử lý ERole enum
            ERole roleType = null;
            String roleTypeStr = rs.getString("role_roleType");
            if (roleTypeStr != null) {
                try {
                    roleType = ERole.valueOf(roleTypeStr);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid role type: " + roleTypeStr);
                }
            }

            role = new Role(
                    rs.getInt("role_id"),
                    roleType,
                    rs.getString("role_name"),
                    rs.getString("role_description"),
                    rs.getBoolean("role_isActive")
            );
        }

        // Tạo đối tượng User
        return new User(
                rs.getInt("id"),
                rs.getString("fullName"),
                rs.getString("displayName"),
                rs.getObject("birth", LocalDate.class),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("password"),
                (Integer) rs.getObject("avatarId"),
                role, // Truyền đối tượng Role vào đây
                rs.getString("salt"),
                rs.getString("avatarUrl"),
                rs.getString("status"),
                rs.getString("confirmationToken"),
                rs.getString("facebookId"),
                rs.getBoolean("needRefresh")
        );
    }
}
