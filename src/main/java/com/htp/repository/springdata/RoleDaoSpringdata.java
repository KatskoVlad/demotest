package com.htp.repository.springdata;

import com.htp.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDaoSpringdata extends JpaRepository<HibernateRole, Long> {

    HibernateRole findByRoleName(String roleName);
}
