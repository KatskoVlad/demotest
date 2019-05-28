package com.htp.domain.jdbc;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Long userId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private Timestamp dateRegistr;
    private int age;
    private boolean isBlock;
    private Long roleId;

    public boolean getIsBlock() {
        return isBlock;
    }
}
