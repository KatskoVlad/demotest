package com.htp.controller.springdata;

import com.htp.controller.requests.RoleCreateRequest;
import com.htp.domain.hibernate.HibernateRole;
import com.htp.repository.springdata.RoleDaoSpringdata;
import io.swagger.annotations.ApiParam;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/springdata/roles")
public class RoleControllerSpringdata {

    @Autowired
    private RoleDaoSpringdata springDataRoleDao;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRole>> getRoles() {

        return new ResponseEntity<>(springDataRoleDao.findAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<HibernateRole>> getRoleById(@ApiParam("HibernateRoleDao Path Id") @PathVariable Long id) {
        Optional<HibernateRole> role = springDataRoleDao.findById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateRole> createRole(@RequestBody RoleCreateRequest request) {
        var role = new HibernateRole();
        role.setRoleName(request.getRoleName());

        HibernateRole savedRole = springDataRoleDao.save(role);

        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRole> updateRole(@PathVariable("id") Long roleId,
                                                    @RequestBody RoleCreateRequest request) {
        var optionalHibernateRole = springDataRoleDao.findById(roleId);
        if (optionalHibernateRole.isPresent()) {
            HibernateRole role = optionalHibernateRole.get();

            role.setRoleName(request.getRoleName());

            HibernateRole updatedRole = springDataRoleDao.save(role);
            return new ResponseEntity<>(updatedRole, HttpStatus.OK);
        } else {
            return null;
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRole(@PathVariable("id") Long roleId) {
        springDataRoleDao.deleteById(roleId);
        return new ResponseEntity<>(roleId, HttpStatus.OK);
    }
}
