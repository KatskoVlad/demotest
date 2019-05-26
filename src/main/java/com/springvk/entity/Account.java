package com.springvk.entity;

import lombok.Data;

import java.security.Timestamp;

@Data
public class Account {
    private Long idAccount;
    private String typeAccount;
    private Double balans;
    private Timestamp dateRegAcc;
    private Long idUser;
}
