package com.springvk.entity;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String surName;
    private String email;
    private int age;
    private Long idRole;
    private boolean isBlocked;
    private Date dateReg;

    private String login;
    private String password;

    public User() {
    }

    public User(Long id, String name, String surName, String email, int age, Long idRole, boolean isBlocked, String login, String password, Date dateReg) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.age = age;
        this.idRole = idRole;
        this.isBlocked = isBlocked;
        this.login = login;
        this.password = password;
        this.dateReg = dateReg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
