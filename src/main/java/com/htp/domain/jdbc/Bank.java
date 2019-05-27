package com.htp.domain.jdbc;

import lombok.Data;

@Data
public class Bank {
    private Long idBank;
    private String codeBic;
    private String description;
    private String inn;
    private String name;
    private String adress;
    private String phone;
}
