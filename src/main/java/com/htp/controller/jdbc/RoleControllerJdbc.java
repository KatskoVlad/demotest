package com.htp.controller.jdbc;

import com.htp.controller.requests.RoleCreateRequest;
import com.htp.domain.jdbc.Role;
import com.htp.repository.jdbc.RoleDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/jdbc/roles")
public class RoleControllerJdbc {

    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;

    @ApiOperation(value = "Get role from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting role"),
            @ApiResponse(code = 400, message = "Invalid Role ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Role was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Role>> getRoles() {

        return new ResponseEntity<>(roleDao.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@ApiParam("HibernateRoleDao Path Id") @PathVariable Long id) {
        Role role = roleDao.findById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody RoleCreateRequest request) {
        Role role = new Role();
        role.setRoleName(request.getRoleName());

        Role savedRole = roleDao.save(role);

        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }
    @ApiOperation(value = "Update role by roleID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long roleId,
                                           @RequestBody RoleCreateRequest request) {
        Role role = roleDao.findById(roleId);
        role.setRoleName(request.getRoleName());

        Role updatedRole = roleDao.update(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRole(@PathVariable("id") Long roleId) {
        roleDao.delete(roleId);
        return new ResponseEntity<>(roleId, HttpStatus.OK);
    }
}