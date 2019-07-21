package com.htp.controller.hibernate;

import com.htp.controller.requests.PerevodCreateRequest;
import com.htp.domain.hibernate.HibernatePerevod;
import com.htp.repository.hibernate.HibernateAccountDao;
import com.htp.repository.hibernate.HibernateCardDao;
import com.htp.repository.hibernate.HibernatePerevodDao;
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
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate/perevods")
public class PerevodControllerHibernate {

    @Autowired
    private HibernatePerevodDao hibernatePerevodDaoImpl;
    @Autowired
    private HibernateAccountDao hibernateAccount;
    @Autowired
    private HibernateCardDao hibernateCardDao1;
    @Autowired
    private HibernateCardDao hibernateCardDao2;

    @GetMapping("/all_hibernate_user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernatePerevod>> getPerevodsHibernate() {
        return new ResponseEntity<>(hibernatePerevodDaoImpl.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get perevod from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting perevod"),
            @ApiResponse(code = 400, message = "Invalid HibernatePerevod ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "HibernatePerevod was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernatePerevod> getPerevodById(@ApiParam("HibernatePerevod Path Id") @PathVariable Long id) {
        HibernatePerevod hibernatePerevod = hibernatePerevodDaoImpl.findById(id);
        return new ResponseEntity<>(hibernatePerevod, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernatePerevod> createPerevod(@RequestBody PerevodCreateRequest request) {
        HibernatePerevod hibernatePerevod = new HibernatePerevod();

        hibernatePerevod.setDatePerevoda(new Timestamp(System.currentTimeMillis()));
        hibernatePerevod.setDateSrokCard(request.getSrokCard());
        hibernatePerevod.setHibernateAccount(hibernateAccount.findByAccount(request.getAccount()));
        hibernatePerevod.setHibernateCard1(hibernateCardDao1.findByNumberCard(request.getTypeCard1()));
        hibernatePerevod.setHibernateCard2(hibernateCardDao2.findByNumberCard(request.getTypeCard2()));
        hibernatePerevod.setKomisyaPerevoda(request.getKomisya());
        hibernatePerevod.setSummaPerevod(request.getSummaPerevoda());
        hibernatePerevod.setTypeCard1(request.getTypeCard1());
        hibernatePerevod.setTypeCard2(request.getTypeCard2());
        hibernatePerevod.setValutaPerevoda(request.getValuta());

        HibernatePerevod savedAccount = hibernatePerevodDaoImpl.save(hibernatePerevod);

        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    @ApiOperation(value = "Update perevod by perevodID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernatePerevod> updatePerevod(@PathVariable("id") Long PerevodId,
                                                          @RequestBody PerevodCreateRequest request) {
        HibernatePerevod hibernatePerevod = hibernatePerevodDaoImpl.findById(PerevodId);
        hibernatePerevod.setDatePerevoda(new Timestamp(System.currentTimeMillis()));
        hibernatePerevod.setDateSrokCard(request.getSrokCard());
        hibernatePerevod.setHibernateAccount(hibernateAccount.findByAccount(request.getAccount()));
        hibernatePerevod.setHibernateCard1(hibernateCardDao1.findByNumberCard(request.getTypeCard1()));
        hibernatePerevod.setHibernateCard2(hibernateCardDao2.findByNumberCard(request.getTypeCard2()));
        hibernatePerevod.setKomisyaPerevoda(request.getKomisya());
        hibernatePerevod.setSummaPerevod(request.getSummaPerevoda());
        hibernatePerevod.setTypeCard1(request.getTypeCard1());
        hibernatePerevod.setTypeCard2(request.getTypeCard2());
        hibernatePerevod.setValutaPerevoda(request.getValuta());

        HibernatePerevod updatePerevod = hibernatePerevodDaoImpl.update(hibernatePerevod);
        return new ResponseEntity<>(updatePerevod, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deletePerevod(@PathVariable("id") Long id) {
        hibernatePerevodDaoImpl.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
