package com.htp.controller.requests;

import lombok.Data;

@Data
public class CardCreateRequest {

    private String numberCard;
    private String account;
    private String srokCard;
    private String securityCode;
    private String nameClienta;
    private String surnameClienta;
    private String codeBank;
    private String nameBank;



}
