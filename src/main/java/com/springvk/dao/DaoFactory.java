package com.springvk.dao;

public abstract class DaoFactory {

    public abstract RoleDao getRoleDao();
    public abstract UserDao getUserDao();

}
