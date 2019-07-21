package com.htp.controller.jdbc;

import com.htp.controller.requests.BankCreateRequest;
import com.htp.domain.jdbc.Bank;
import com.htp.repository.jdbc.CatalogBanksDao;
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
@RequestMapping(value = "/rest/jdbc/banks")
public class CatalogBankControllerJdbc {

    @Autowired
    @Qualifier("catalogBanksDaoImpl")
    private CatalogBanksDao catalogBanksDaoImpl;

    @ApiOperation(value = "Get bank from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting bank"),
            @ApiResponse(code = 400, message = "Invalid bank ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "bank was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Bank>> getBankAll() {
        return new ResponseEntity<>(catalogBanksDaoImpl.findAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bank> getBankById(@ApiParam("BankDao Jdbc Path Id") @PathVariable Long id) {
        Bank bank = catalogBanksDaoImpl.findById(id);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bank> createBank(@RequestBody BankCreateRequest request) {
        Bank bank = new Bank();
        bank.setCodeBic(request.getCodeBic());
        bank.setDescription(request.getDescription());
        bank.setName(request.getNameBank());
        bank.setPhone(request.getPhone());
        bank.setAdress(request.getAdress());
        bank.setInn(request.getInn());

        Bank savedBank = catalogBanksDaoImpl.save(bank);

        return new ResponseEntity<>(savedBank, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Bank> updateCatalogBanks(@PathVariable("id") Long bankId,
                                           @RequestBody BankCreateRequest request) {
        Bank bank = catalogBanksDaoImpl.findById(bankId);
        bank.setCodeBic(request.getCodeBic());
        bank.setDescription(request.getDescription());
        bank.setPhone(request.getPhone());
        bank.setAdress(request.getAdress());
        bank.setInn(request.getInn());

        Bank updatedBank = catalogBanksDaoImpl.update(bank);
        return new ResponseEntity<>(updatedBank, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteBank(@PathVariable("id") Long bankId) {
        catalogBanksDaoImpl.delete(bankId);
        return new ResponseEntity<>(bankId, HttpStatus.OK);
    }
}
