package com.springvk.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private static final int serialVersionUID = 1;

    private int roleId;
    private String roleName;
    private int userId;

    public Role(){}

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Role(int roleId, String roleName, int userId) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static int getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (roleId != role.roleId) {
            return false;
        }

        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return (int)(7 * this.roleId + 7 * this.roleName.hashCode());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("\n");
        sb.append("Role ID: ");
        sb.append(this.roleId);
        sb.append("\n");
        sb.append("\n");
        sb.append("Role name: ");
        sb.append(this.roleName);
        sb.append("\n");
        return sb.toString();
    }
}