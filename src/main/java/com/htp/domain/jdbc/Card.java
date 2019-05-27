package com.htp.domain.jdbc;

import lombok.Data;

@Data
public class Card {
    private Long idCard;
    private Long idUser;
    private String numCard;
    private String srokCard;
    private String securityCode;
    private Long idAccount;
    private Long idBank;
}
