package com.htp.domain.jdbc;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
public class User {

    private Long userId;

    @NotBlank(message = "This field can`t be empty")
    private String name;

    @NotBlank(message = "This field can`t be empty")
    private String surname;

    @Size(min = 7, max = 20, message = "Wrong size of login")
    private String login;

    @Size(min = 7, max = 20, message = "Wrong size of password")
    private String password;

    @Email(message = "Not a valid email")
    private String email;

    private Timestamp dateRegistr;
    private int age;
    private boolean isBlock;
    private Long idRole;

    public boolean getIsBlock() {
        return isBlock;
    }
}
