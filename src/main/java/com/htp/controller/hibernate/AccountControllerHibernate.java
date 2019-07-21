package com.htp.controller.hibernate;

import com.htp.controller.requests.AccountCreateRequest;
import com.htp.domain.hibernate.HibernateAccount;
import com.htp.repository.hibernate.HibernateAccountDao;
import com.htp.repository.hibernate.HibernateUserDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate/accounts")
public class AccountControllerHibernate {

    @Autowired
    private HibernateAccountDao hibernateAccountDaoImpl;

    @Autowired
    private HibernateUserDao hibernateUserDaoImpl;

    @GetMapping("/all_hibernate_account")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateAccount>> getAccountsHibernate() {
        return new ResponseEntity<>(hibernateAccountDaoImpl.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get account from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting account"),
            @ApiResponse(code = 400, message = "Invalid HibernateAccount ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "HibernateAccount was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateAccount> getAccountById(@ApiParam("HibernateAccount Path Id") @PathVariable Long id) {
        HibernateAccount hibernateAccount = hibernateAccountDaoImpl.findById(id);
        return new ResponseEntity<>(hibernateAccount, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateAccount> createAccount(@RequestBody AccountCreateRequest request) {
        HibernateAccount hibernateAccount = new HibernateAccount();

        hibernateAccount.setAccountName(request.getAccount());
        hibernateAccount.setCreateDateAccount(new Timestamp(new Date().getTime()));
        hibernateAccount.setBalansAccount(request.getBalans());
        hibernateAccount.setAccountUser(hibernateUserDaoImpl.findByNameAndSurname(request.getSurname(), request.getName()));

        HibernateAccount savedAccount = hibernateAccountDaoImpl.save(hibernateAccount);

        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    @ApiOperation(value = "Update account by accountID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateAccount> updateAccount(@PathVariable("id") Long accountId,
                                                          @RequestBody AccountCreateRequest request) {
        HibernateAccount hibernateAccount = hibernateAccountDaoImpl.findById(accountId);
        hibernateAccount.setAccountName(request.getAccount());
        hibernateAccount.setCreateDateAccount(new Timestamp(new Date().getTime()));
        hibernateAccount.setBalansAccount(request.getBalans());
        hibernateAccount.setAccountUser(hibernateUserDaoImpl.findByNameAndSurname(request.getSurname(), request.getName()));
        HibernateAccount updateAccount = hibernateAccountDaoImpl.update(hibernateAccount);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteAccount(@PathVariable("id") Long id) {
        hibernateAccountDaoImpl.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}