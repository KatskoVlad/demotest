package com.htp.domain.hibernate;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "bank_perevod")
public class HibernatePerevod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perevod")
    private Long perevodId;

    @Column(name = "type_card_1")
    private String typeCard1;

    @Column(name = "type_card_2")
    private String typeCard2;

    @Column(name = "srok_card")
    private Date dateSrokCard;

    @Column(name = "security_code_1")
    private String securityCode;

    @Column(name = "summa")
    private double summaPerevod;

    @Column(name = "valuta")
    private String valutaPerevoda;

    @Column(name = "stavka")
    private double stavkaPerevoda;

    @Column(name = "komisya")
    private double komisyaPerevoda;

    @Column(name = "data_perevoda")
    private Timestamp datePerevoda;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private HibernateAccount hibernateAccount;

//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_bank")
//    private HibernateBank hibernateBank;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_card_1")
    private HibernateCard hibernateCard1;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_card_2")
    private HibernateCard hibernateCard2;

}
