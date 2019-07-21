package com.htp.controller.hibernate;

import com.htp.controller.requests.CardCreateRequest;
import com.htp.domain.hibernate.HibernateCard;
import com.htp.repository.hibernate.HibernateAccountDao;
import com.htp.repository.hibernate.HibernateBankDao;
import com.htp.repository.hibernate.HibernateCardDao;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate/cards")
public class CardControllerHibernate {

    @Autowired
    private HibernateCardDao hibernateCardDaoImpl;

    @Autowired
    private HibernateBankDao hibernateBankDaoImpl;

    @Autowired
    private HibernateUserDao hibernateUserDaoImpl;

    @Autowired
    private HibernateAccountDao hibernateAccountDao;

    @GetMapping("/all_hibernate_card")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCard>> getAccountsHibernate() {
        return new ResponseEntity<>(hibernateCardDaoImpl.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get card from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting card"),
            @ApiResponse(code = 400, message = "Invalid HibernateCard ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "HibernateCard was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateCard> getCardById(@ApiParam("HibernateCard Path Id") @PathVariable Long id) {
        HibernateCard hibernateCard = hibernateCardDaoImpl.findById(id);
        return new ResponseEntity<>(hibernateCard, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateCard> createCard(@RequestBody CardCreateRequest request) {
        HibernateCard hibernateCard = new HibernateCard();

        hibernateCard.setNumberCard(request.getNumberCard());
        hibernateCard.setHibernateBank(hibernateBankDaoImpl.findByNameBank(request.getNameBank()).get(0));
        hibernateCard.setSecurityCode(request.getSecurityCode());
        hibernateCard.setSrokCard(new Date(new Timestamp(System.currentTimeMillis()).getTime()));
        hibernateCard.setCardUser(hibernateUserDaoImpl.findByNameAndSurname(request.getNameClienta(), request.getSurnameClienta()));
        hibernateCard.setHibernateAccount(hibernateAccountDao.findByAccount(request.getAccount()));

        HibernateCard savedAccount = hibernateCardDaoImpl.save(hibernateCard);

        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    @ApiOperation(value = "Update card by cardID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCard> updateAccount(@PathVariable("id") Long cardId,
                                                       @RequestBody CardCreateRequest request) {
        HibernateCard hibernateCard = hibernateCardDaoImpl.findById(cardId);
        hibernateCard.setNumberCard(request.getNumberCard());
        hibernateCard.setHibernateBank(hibernateBankDaoImpl.findByNameBank(request.getNameBank()).get(0));
        hibernateCard.setSecurityCode(request.getSecurityCode());
        hibernateCard.setSrokCard(new Date(new Timestamp(System.currentTimeMillis()).getTime()));
        hibernateCard.setCardUser(hibernateUserDaoImpl.findByNameAndSurname(request.getNameClienta(), request.getSurnameClienta()));
        hibernateCard.setHibernateAccount(hibernateAccountDao.findByAccount(request.getAccount()));

        HibernateCard updateCard = hibernateCardDaoImpl.update(hibernateCard);
        return new ResponseEntity<>(updateCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCard(@PathVariable("id") Long id) {
        hibernateCardDaoImpl.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
