package com.htp.controller.jdbc;

import com.htp.controller.requests.PerevodCreateRequest;
import com.htp.domain.jdbc.Perevod;
import com.htp.repository.jdbc.AccountDao;
import com.htp.repository.jdbc.CardDao;
import com.htp.repository.jdbc.CatalogBanksDao;
import com.htp.repository.jdbc.PerevodDao;
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

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/jdbc/perevods")
public class PerevodControllerJdbc {

    @Autowired
    @Qualifier("perevodDaoImpl")
    private PerevodDao perevodDaoImpl;

    @Autowired
    @Qualifier("cardDaoImpl")
    private CardDao cardDaoImpl;

    @Autowired
    @Qualifier("accountDaoImpl")
    private AccountDao accountDaoImpl;

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDaoImpl;

    @Autowired
    @Qualifier("catalogBanksDaoImpl")
    private CatalogBanksDao catalogBanksDaoImpl;

    @ApiOperation(value = "Get perevod from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting perevod"),
            @ApiResponse(code = 400, message = "Invalid perevod ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Perevod was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Perevod>> getPerevod() {
        return new ResponseEntity<>(perevodDaoImpl.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Perevod> getPerevodById(@ApiParam("PerevodDao Jdbc Path Id") @PathVariable Long id) {
        Perevod perevod = perevodDaoImpl.findById(id);
        return new ResponseEntity<>(perevod, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Perevod> createPerevod(@RequestBody PerevodCreateRequest request) {
        Perevod perevod = new Perevod();
        perevod.setIdCard1(cardDaoImpl.findByNumberCard(request.getNumberCard1()));
        perevod.setIdCard2(cardDaoImpl.findByNumberCard(request.getNumberCard2()));
        perevod.setTypeCard1(request.getTypeCard1());
        perevod.setTypeCard2(request.getTypeCard2());
        perevod.setSrokCard(request.getSrokCard());
        perevod.setSucurityCode1(request.getSecurityCode());
        perevod.setSummaPerevoda(request.getSummaPerevoda());
        perevod.setValuta(request.getValuta());
        perevod.setStavka(request.getStavka());
        perevod.setKomisya(request.getKomisya());
        perevod.setDatePerevoda(new Timestamp(System.currentTimeMillis()));
        perevod.setIdBank(catalogBanksDaoImpl.findByCodeBic(request.getCodeBank()));

        Perevod savedPerevod = perevodDaoImpl.save(perevod);

        return new ResponseEntity<>(savedPerevod, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Perevod> updatePerevod(@PathVariable("id") Long perevodId,
                                                 @RequestBody PerevodCreateRequest request) {
        Perevod perevod = new Perevod();
        perevod.setIdCard1(cardDaoImpl.findByNumberCard(request.getNumberCard1()));
        perevod.setIdCard2(cardDaoImpl.findByNumberCard(request.getNumberCard2()));
        perevod.setTypeCard1(request.getTypeCard1());
        perevod.setTypeCard2(request.getTypeCard2());
        perevod.setSrokCard(request.getSrokCard());
        perevod.setSucurityCode1(request.getSecurityCode());
        perevod.setSummaPerevoda(request.getSummaPerevoda());
        perevod.setValuta(request.getValuta());
        perevod.setStavka(request.getStavka());
        perevod.setKomisya(request.getKomisya());
        perevod.setDatePerevoda(new Timestamp(System.currentTimeMillis()));
        perevod.setIdBank(catalogBanksDaoImpl.findByCodeBic(request.getCodeBank()));

        Perevod updatedPerevod = perevodDaoImpl.update(perevod);
        return new ResponseEntity<>(updatedPerevod, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deletePerevod(@PathVariable("id") Long perevodId) {
        perevodDaoImpl.delete(perevodId);
        return new ResponseEntity<>(perevodId, HttpStatus.OK);
    }
}