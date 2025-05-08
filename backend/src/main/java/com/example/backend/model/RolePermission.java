package com.example.backend.model;

import org.checkerframework.checker.units.qual.C;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class RolePermission {
    Integer id;
    Integer roleId;
    Integer permissionId;

    public RolePermission(
            @ColumnName("id") Integer id,
            @ColumnName("roleId") Integer roleId,
            @ColumnName("permissionId") Integer permissionId

    ) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
