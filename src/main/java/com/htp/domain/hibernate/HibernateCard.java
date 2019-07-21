package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "reestr_bank_card")
public class HibernateCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Long idCard;

    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "srok_mm_yy")
    private Date srokCard;

    @Column(name = "security_code")
    private String securityCode;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private HibernateUser cardUser;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_account")
    private HibernateAccount hibernateAccount;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bank")
    private HibernateBank hibernateBank;

    @JsonBackReference
    @OneToMany(mappedBy = "hibernateCard1", fetch = FetchType.EAGER)
    private List<HibernatePerevod> hibernatePerevod1 = Collections.emptyList();

    @JsonBackReference
    @OneToMany(mappedBy = "hibernateCard2", fetch = FetchType.EAGER)
    private List<HibernatePerevod> hibernatePerevod2 = Collections.emptyList();


}
