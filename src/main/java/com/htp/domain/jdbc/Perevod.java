package com.htp.domain.jdbc;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Perevod {
    private Long idPerevoda;
    private Long idCard1;
    private Long idCard2;
    private String typeCard1;
    private String typeCard2;
    private Timestamp srokCard;
    private String sucurityCode1;
    private Long idAccount;
    private Double summaPerevoda;
    private String valuta;
    private Double stavka;
    private Double komisya;
    private Timestamp datePerevoda;
    private Long idBank;
    private Long idUser;
}
