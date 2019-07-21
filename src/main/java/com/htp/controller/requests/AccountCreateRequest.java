package com.htp.controller.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountCreateRequest {

        private String account;
        private String typeAccount;
        private Double balans;
        private Timestamp createDate;
        private String name;
        private String surname;

}
