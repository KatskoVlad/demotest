package com.htp.domain.jdbc;

import lombok.Data;

import java.util.Date;

@Data
public class Perevod {
    private Long idPerevoda;
    private Long idCard1;
    private Long idCard2;
    private String typeCard1;
    private String typeCard2;
    private Date srokCard;
    private String sucurityCode1;
    private String sucurityCode2;
    private String idAccount;
    private Double summaPerevoda;
    private String valuta;
    private Double stavka;
    private Double komisya;
    private Date dataPerevoda;
    private Long idBank;
    private Long idUser;
}
