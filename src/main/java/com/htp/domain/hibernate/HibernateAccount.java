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

@Data
@Entity
@Table(name = "accounts")
public class HibernateAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accounts")
    private Long accountId;

    @Column(name = "account")
    private String accountName;

    @Column(name = "balans")
    private double balansAccount;

    @Column(name = "date_reg")
    private String createDateAccount;

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private HibernateUser accountUser;

}
