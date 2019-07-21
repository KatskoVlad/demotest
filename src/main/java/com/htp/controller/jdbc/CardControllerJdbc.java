package com.htp.controller.jdbc;

import com.htp.controller.requests.CardCreateRequest;
import com.htp.domain.jdbc.Card;
import com.htp.repository.jdbc.AccountDao;
import com.htp.repository.jdbc.CardDao;
import com.htp.repository.jdbc.CatalogBanksDao;
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
@RequestMapping(value = "/rest/jdbc/cards")
public class CardControllerJdbc {

    @Autowired
    @Qualifier("cardDaoImpl")
    private CardDao cardDaoImpl;

    @Autowired
    @Qualifier("catalogBanksDaoImpl")
    private CatalogBanksDao catalogBanksDaoImpl;

    @Autowired
    @Qualifier("accountDaoImpl")
    private AccountDao accountDaoImpl;

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDaoImpl;

    @ApiOperation(value = "Get card from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting card"),
            @ApiResponse(code = 400, message = "Invalid card ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Card was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Card>> getCard() {
        return new ResponseEntity<>(cardDaoImpl.findAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Card> getCardById(@ApiParam("CardDao Jdbc Path Id") @PathVariable Long id) {
        Card card = cardDaoImpl.findById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Card> createCard(@RequestBody CardCreateRequest request) {
        Card card = new Card();
        card.setIdBank(catalogBanksDaoImpl.findByCodeBic(request.getNameBank()));
        card.setIdUser(userDaoImpl.findBySurname(request.getSurnameClienta()));
        card.setIdAccount(accountDaoImpl.findByAccount(request.getAccount()));
        card.setNumCard(request.getNumberCard());
        card.setSrokCard(request.getSrokCard());
        card.setSecurityCode(request.getSecurityCode());

        Card savedCard = cardDaoImpl.save(card);
        return new ResponseEntity<>(savedCard, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Card> updateCard(@PathVariable("id") Long cardId,
                                           @RequestBody CardCreateRequest request) {
        Card card = cardDaoImpl.findById(cardId);
        card.setIdBank(catalogBanksDaoImpl.findByCodeBic(request.getNameBank()));
        card.setIdUser(userDaoImpl.findBySurname(request.getSurnameClienta()));
        card.setIdAccount(accountDaoImpl.findByAccount(request.getAccount()));
        card.setNumCard(request.getNumberCard());
        card.setSrokCard(request.getSrokCard());
        card.setSecurityCode(request.getSecurityCode());

        Card updatedCard = cardDaoImpl.update(card);
        return new ResponseEntity<>(updatedCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCard(@PathVariable("id") Long cardId) {
        cardDaoImpl.delete(cardId);
        return new ResponseEntity<>(cardId, HttpStatus.OK);
    }
}