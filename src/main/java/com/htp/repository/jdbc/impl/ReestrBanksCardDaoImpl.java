package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.Card;
import com.htp.repository.jdbc.ReestrBanksCardDao;
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
@Qualifier("reestrBanksCardDaoImpl")
public class ReestrBanksCardDaoImpl implements ReestrBanksCardDao {

    private static final String ID_CARD = "id_card";
    private static final String ID_USER = "id_user";
    private static final String NUMBER_CARD = "number_card";
    private static final String SROK_CARD = "srok_mm_yy";
    private static final String SECURITY_CODE = "security_code";
    private static final String ID_ACCOUNT = "id_account";

    final String findAllQuery = "select * from reestr_bank_card";
    final String findById = "select * from reestr_bank_card where id_card = :idCard";
    final String deleteCard = "delete from reestr_bank_card where id_card = :idCard";
    final String createQueryUpdate = "UPDATE reestr_bank_card set code_bic=:codeBic, " +
                                     "description=:description, inn=:inn, name=:name, adress=:adress, phone=:phone where id_bank = :idBank";
    final String createQueryInsert = "INSERT INTO reestr_bank_card (id_user, number_card, srok_mm_yy, security_code, id_account, id_bank) " +
                                     "VALUES (:idUser, :numberCard, :srokMmYy, :securityCode, :idAccount, :idAccount, :idBank);";
    final String findByNumberCardStr = "select * from reestr_bank_card where code_bic = :codeBic";
    final String searchQuery = "select * from reestr_bank_card where " +
                               "lower(number_card) LIKE lower(:query) "+
                               "limit :lim offset :off";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Card getReestrBankCardRowMapper(ResultSet resultSet, int i) throws SQLException {

        Card card = new Card();

        card.setIdBank(resultSet.getLong(ID_CARD));
        card.setIdUser(resultSet.getLong(ID_USER));
        card.setNumCard(resultSet.getString(NUMBER_CARD));
        card.setSrokCard(resultSet.getString(SROK_CARD));
        card.setSecurityCode(resultSet.getString(SECURITY_CODE));
        card.setIdAccount(resultSet.getLong(ID_ACCOUNT));

        return card;
    }

    @Override
    public List<Card> findAll() {
        return namedParameterJdbcTemplate.query(findAllQuery, this::getReestrBankCardRowMapper);
    }

    @Override
    public Card findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCard", id);
        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getReestrBankCardRowMapper);
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
        params.addValue("numberCard", card.getNumCard());
        params.addValue("srokMmYy", card.getSrokCard());
        params.addValue("securityCode", card.getSecurityCode());
        params.addValue("idAccount", card.getIdAccount());
        params.addValue("idBank", card.getIdBank());

        namedParameterJdbcTemplate.update(createQueryInsert, params, keyHolder);

        long createdCardId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdCardId);
    }

    @Override
    public Card update(Card card) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idUser", card.getIdUser());
        params.addValue("numberCard", card.getNumCard());
        params.addValue("srokMmYy", card.getSrokCard());
        params.addValue("securityCode", card.getSecurityCode());
        params.addValue("idAccount", card.getIdAccount());
        params.addValue("idBank", card.getIdBank());

        params.addValue("idCard", card.getIdCard());

        namedParameterJdbcTemplate.update(createQueryUpdate, params);
        return findById(card.getIdCard());
    }

    @Override
    public Card findByNumberCard(String numberCard) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("numberCard", numberCard);
        return namedParameterJdbcTemplate.queryForObject(findByNumberCardStr, params, this::getReestrBankCardRowMapper);
    }

    @Override
    public List<Card> search(String query, Integer limit, Integer offset) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getReestrBankCardRowMapper);
    }
}
