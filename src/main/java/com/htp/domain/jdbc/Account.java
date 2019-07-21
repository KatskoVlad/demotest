package com.htp.domain.jdbc;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Account {
    private Long idAccount;
    private String typeAccount;
    private Double balans;
    private Timestamp dateRegAcc;
    private Long idUser;
}
