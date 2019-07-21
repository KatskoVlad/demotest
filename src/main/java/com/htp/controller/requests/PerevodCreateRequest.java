package com.htp.controller.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PerevodCreateRequest {

    private String account;
    private String numberCard1;
    private String numberCard2;
    private String typeCard1;
    private String typeCard2;
    private String securityCode;
    private Timestamp srokCard;
    private Double summaPerevoda;
    private String valuta;
    private Double stavka;
    private Double komisya;
    private String nameClienta;
    private String surnameClienta;
    private String codeBank;
    private String nameBanka;

}
