package com.htp.controller.hibernate;

import com.htp.controller.requests.BankCreateRequest;
import com.htp.domain.hibernate.HibernateBank;
import com.htp.repository.hibernate.HibernateBankDao;
import com.htp.repository.hibernate.HibernateCardDao;
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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate/banks")
public class CatalogBankControllerHibernate {

    @Autowired
    private HibernateBankDao hibernateBankDaoImpl;

    @Autowired
    private HibernateCardDao hibernateCardDaoImpl;

    @GetMapping("/all_hibernate_user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateBank>> getBanksHibernate() {
        return new ResponseEntity<>(hibernateBankDaoImpl.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get bank from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting bank"),
            @ApiResponse(code = 400, message = "Invalid HibernateBank ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "HibernateBank was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateBank> getBankById(@ApiParam("HibernateBank Path Id") @PathVariable Long id) {
        HibernateBank hibernateBank = hibernateBankDaoImpl.findById(id);
        return new ResponseEntity<>(hibernateBank, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateBank> createBank(@RequestBody BankCreateRequest request) {
        HibernateBank hibernateBank = new HibernateBank();

        hibernateBank.setAdress(request.getAdress());
        hibernateBank.setCodeBic(request.getCodeBic());
        hibernateBank.setDescription(request.getDescription());
        hibernateBank.setInnBank(request.getInn());
        hibernateBank.setNameBank(request.getNameBank());
        hibernateBank.setPhone(request.getPhone());

        HibernateBank savedAccount = hibernateBankDaoImpl.save(hibernateBank);

        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    @ApiOperation(value = "Update account by accountID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateBank> updateAccount(@PathVariable("id") Long bankId,
                                                       @RequestBody BankCreateRequest request) {
        HibernateBank hibernateBank = hibernateBankDaoImpl.findById(bankId);
        hibernateBank.setAdress(request.getAdress());
        hibernateBank.setCodeBic(request.getCodeBic());
        hibernateBank.setDescription(request.getDescription());
        hibernateBank.setInnBank(request.getInn());
        hibernateBank.setNameBank(request.getNameBank());
        hibernateBank.setPhone(request.getPhone());

        HibernateBank updateBank = hibernateBankDaoImpl.update(hibernateBank);
        return new ResponseEntity<>(updateBank, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteBank(@PathVariable("id") Long id) {
        hibernateBankDaoImpl.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
