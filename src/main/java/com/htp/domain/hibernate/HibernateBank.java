package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "catalog_banks")
public class HibernateBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank")
    private Long idBank;

    @Column(name = "code_bic")
    private String codeBic;

    @Column(name = "description")
    private String description;

    @Column(name = "inn")
    private String innBank;

    @Column(name = "name")
    private String nameBank;

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone")
    private String phone;

    @JsonBackReference
    @OneToMany(mappedBy = "hibernateBank", fetch = FetchType.EAGER)
    private List<HibernateCard> hibernateCard;

//    @JsonBackReference
//    @OneToMany(mappedBy = "hibernateBank", fetch = FetchType.EAGER)
//    private List<HibernatePerevod> hibernatePerevod;

}
