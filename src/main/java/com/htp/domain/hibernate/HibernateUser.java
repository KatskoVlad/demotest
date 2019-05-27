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
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class HibernateUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "is_bloked")
    private boolean isBloked;

    @Column(name = "date_registr")
    private Timestamp creationDate;

    @Column(name = "email")
    private String email;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    private HibernateRole hibernateRole;

//    @JsonBackReference
//    @OneToMany(mappedBy = "id_accounts", fetch = FetchType.EAGER)
//    private List<HibernateAccount> hibernateShipments = Collections.emptyList();

}
