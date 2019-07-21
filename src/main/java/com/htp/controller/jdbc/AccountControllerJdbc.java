package com.htp.controller.jdbc;

import com.htp.controller.requests.AccountCreateRequest;
import com.htp.domain.jdbc.Account;
import com.htp.repository.jdbc.AccountDao;
import com.htp.repository.jdbc.UserDao;
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
@RequestMapping(value = "/rest/jdbc/accounts")
public class AccountControllerJdbc {

    @Autowired
    @Qualifier("accountDaoImpl")
    private AccountDao accountDaoImpl;

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDaoImpl;

    @ApiOperation(value = "Get account from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting account"),
            @ApiResponse(code = 400, message = "Invalid account ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Account was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Account>> getAccount() {
        return new ResponseEntity<>(accountDaoImpl.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getAccountById(@ApiParam("AccountDao Jdbc Path Id") @PathVariable Long id) {
        Account account = accountDaoImpl.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreateRequest request) {
        Account account = new Account();
        account.setTypeAccount(request.getTypeAccount());
        account.setDateRegAcc(request.getCreateDate());
        account.setBalans(request.getBalans());
        account.setIdUser(userDaoImpl.findBySurname(request.getSurname()));
        account.setIdAccount(accountDaoImpl.findByAccount(request.getAccount()));

        Account savedAccount = accountDaoImpl.save(account);

        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long accountId,
                                                 @RequestBody AccountCreateRequest request) {
        Account account = accountDaoImpl.findById(accountId);
        account.setTypeAccount(request.getTypeAccount());
        account.setDateRegAcc(request.getCreateDate());
        account.setBalans(request.getBalans());
        account.setIdUser(userDaoImpl.findBySurname(request.getSurname()));
        account.setIdAccount(accountDaoImpl.findByAccount(request.getAccount()));

        Account updatedAccount = accountDaoImpl.update(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteAccount(@PathVariable("id") Long accountId) {
        accountDaoImpl.delete(accountId);
        return new ResponseEntity<>(accountId, HttpStatus.OK);
    }
}