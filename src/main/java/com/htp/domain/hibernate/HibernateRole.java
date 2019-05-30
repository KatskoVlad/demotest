package com.htp.domain.hibernate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
public class HibernateRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "name_role")
    private String roleName;

//    @JsonBackReference
//    @OneToMany(mappedBy = "hibernateRole", fetch = FetchType.EAGER)
//    private List<HibernateUser> hibernateUsers;

}
