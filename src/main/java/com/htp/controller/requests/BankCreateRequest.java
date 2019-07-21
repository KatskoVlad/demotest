package com.htp.controller.requests;

import lombok.Data;

@Data
public class BankCreateRequest {

    private String codeBic;
    private String description;
    private String inn;
    private String nameBank;
    private String adress;
    private String phone;

}
