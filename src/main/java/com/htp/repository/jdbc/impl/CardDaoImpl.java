package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.Card;
import com.htp.repository.jdbc.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@Qualifier("cardDaoImpl")
public class CardDaoImpl implements CardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID_CARD = "id_card";
    private static final String ID_USER = "id_user";
    private static final String NUM_CARD = "number_card";
    private static final String DATE_SROK = "srok_mm_yy";
    private static final String SECURITY_CODE = "security_code";
    private static final String ID_ACCOUNT = "id_account";
    private static final String ID_BANK = "id_bank";

    final String findAllQuery = "select * from reestr_bank_card";
    final String deleteCard = "delete from reestr_bank_card where id_card = :idCard";
    final String createQueryUpdate = "UPDATE reestr_bank_card set code_bic=:codeBic, " +
            "description=:description, inn=:inn, name=:name, adress=:adress, phone=:phone where id_bank = :idBank";
    final String createQueryInsert = "INSERT INTO reestr_bank_card (id_card, id_user, number_card, srok_mm_yy, security_code, id_account, id_bank) " +
            "VALUES (:idCard, :idUser, :numCard, :srokCard, :securityCode, :idAccount, :idBank);";
    final String findByIdCard = "select * from reestr_bank_card where id_card = :idCard";
    final String searchQuery = "select * from reestr_bank_card where lower(number_card) LIKE lower(:query) " +
            "limit :lim offset :off";
    final String findByCardStr = "select * from accounts where account = :account";


    private Card getCardRowMapper(ResultSet resultSet, int i) throws SQLException {

        Card card = new Card();

        card.setIdCard(resultSet.getLong(ID_CARD));
        card.setIdAccount(resultSet.getLong(ID_ACCOUNT));
        card.setSecurityCode(resultSet.getString(SECURITY_CODE));
        card.setSrokCard(resultSet.getString(DATE_SROK));
        card.setNumCard(resultSet.getString(NUM_CARD));
        card.setIdUser(resultSet.getLong(ID_USER));
        card.setIdBank(resultSet.getLong(ID_BANK));

        return card;
    }

    @Override
    public List<Card> findAll() {
        return namedParameterJdbcTemplate.query(findAllQuery, this::getCardRowMapper);
    }

    @Override
    public Card findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCard", id);
        return namedParameterJdbcTemplate.queryForObject(findByIdCard, params, this::getCardRowMapper);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCard", id);
        namedParameterJdbcTemplate.update(deleteCard, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Card save(Card card) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idUser", card.getIdUser());
        params.addValue("numCard", card.getNumCard());
        params.addValue("srokCard", card.getSrokCard());
        params.addValue("securityCode", card.getSecurityCode());
        params.addValue("idAccount", card.getIdAccount());
        params.addValue("idBank", card.getIdBank());
        namedParameterJdbcTemplate.update(createQueryInsert, params, keyHolder);
        long createdAccountId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdAccountId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Card update(Card card) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idUser", card.getIdUser());
        params.addValue("numCard", card.getNumCard());
        params.addValue("srokCard", card.getSrokCard());
        params.addValue("securityCode", card.getSecurityCode());
        params.addValue("idAccount", card.getIdAccount());
        params.addValue("idBank", card.getIdBank());

        namedParameterJdbcTemplate.update(createQueryUpdate, params);
        return findById(card.getIdCard());
    }

    @Override
    public List<Card> search(String query, Integer limit, Integer offset) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getCardRowMapper);
    }

    @Override
    public Long findByNumberCard(String cardNumber) {
        Card card = new Card();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("numCard", cardNumber);
        card = namedParameterJdbcTemplate.queryForObject(findByCardStr, params, this::getCardRowMapper);
        return card.getIdCard();
    }
}
