package com.htp.controller.requests;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String roleName;
    private String email;
    private int age;

}
