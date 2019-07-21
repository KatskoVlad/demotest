package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.Perevod;
import com.htp.repository.jdbc.PerevodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@Qualifier("perevodDaoImpl")
public class PerevodDaoImpl implements PerevodDao {

    private static final String ID_PEREVODA = "id_perevod";
    private static final String ID_CARD1 = "id_card_1";
    private static final String ID_CARD2 = "id_card_2";
    private static final String TYPE_CARD_1 = "type_card_1";
    private static final String TYPE_CARD_2 = "type_card_2";
    private static final String SROK_CARD = "type_card_2";
    private static final String SECURITY_CODE_1 = "security_code_1";
    private static final String ACCOUNT_ID = "account_id";
    private static final String SUMMA = "summa";
    private static final String VALUTA = "valuta";
    private static final String STAVKA = "stavka";
    private static final String KOMISIA = "komisya";
    private static final String DATE_PEREVODA = "date_perevoda";
    private static final String ID_BANK = "id_bank";
    private static final String ID_USER = "id_user";

    final String findAllQuery = "select * from bank_perevod";
    final String findByIdPerevoda = "select * from bank_perevod  where id_perevod = :idPerevoda";
    final String deletePerevoda = "delete from bank_perevod where id_perevod = :idPerevoda";
    final String createQueryUpdate = "UPDATE bank_perevod set code_bic=:codeBic, " +
                                     "description=:description, inn=:inn, name=:name, adress=:adress, phone=:phone where id_bank = :idBank";
    final String createQueryInsert = "INSERT INTO bank_perevod (id_card_1, id_card_2, type_card_1, type_card_2, srok_card, security_code_1, "+
                                     "account_id, summa, valuta, stavka, komisya, data_perevoda, id_bank, id_user) " +
                                     "VALUES (:idCard1, :idCard2, :typeCard1, :typeCard2, :srokCard, :securityCode1, :phone);";
//    final String findByPerevodaStr = "select * from bank_perevod where account = :account";
    final String searchQuery = "select * from bank_perevod where lower(type_card_1) LIKE lower(:query) or " +
                               "lower(type_card_2) LIKE lower(:query) " +
                               "limit :lim offset :off";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Perevod getPerevodRowMapper(ResultSet resultSet, int i) throws SQLException {

        Perevod perevod = new Perevod();

        perevod.setIdPerevoda(resultSet.getLong(ID_PEREVODA));
        perevod.setIdCard1(resultSet.getLong(ID_CARD1));
        perevod.setIdCard1(resultSet.getLong(ID_CARD2));
        perevod.setTypeCard1(resultSet.getString(TYPE_CARD_1));
        perevod.setTypeCard2(resultSet.getString(TYPE_CARD_2));
        perevod.setSrokCard(resultSet.getTimestamp(SROK_CARD));
        perevod.setSucurityCode1(resultSet.getString(SECURITY_CODE_1));
        perevod.setIdAccount(resultSet.getLong(ACCOUNT_ID));
        perevod.setSummaPerevoda(resultSet.getDouble(SUMMA));
        perevod.setValuta(resultSet.getString(VALUTA));
        perevod.setStavka(resultSet.getDouble(STAVKA));
        perevod.setKomisya(resultSet.getDouble(KOMISIA));
        perevod.setDatePerevoda(resultSet.getTimestamp(DATE_PEREVODA));
        perevod.setIdBank(resultSet.getLong(ID_BANK));
        perevod.setIdUser(resultSet.getLong(ID_USER));

        return perevod;
    }

    @Override
    public List<Perevod> findAll() {
        return namedParameterJdbcTemplate.query(findAllQuery, this::getPerevodRowMapper);

    }

    @Override
    public Perevod findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPerevoda", id);
        return namedParameterJdbcTemplate.queryForObject(findByIdPerevoda, params, this::getPerevodRowMapper);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPerevoda", id);
        namedParameterJdbcTemplate.update(deletePerevoda, params);
    }

    @Override
    public Perevod save(Perevod perevod) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCard1", perevod.getIdCard1());
        params.addValue("idCard2", perevod.getIdCard2());
        params.addValue("typeCard1", perevod.getTypeCard1());
        params.addValue("typeCard2", perevod.getTypeCard1());
        params.addValue("srokCard", perevod.getSrokCard());
        params.addValue("securityCode1", perevod.getSucurityCode1());
        params.addValue("accountId", perevod.getIdAccount());
        params.addValue("summaPerevoda", perevod.getSummaPerevoda());
        params.addValue("valutaPerevoda", perevod.getValuta());
        params.addValue("stavkaPerevoda", perevod.getStavka());
        params.addValue("komisyaPerevoda", perevod.getStavka());
        params.addValue("datePerevoda", perevod.getDatePerevoda());
        params.addValue("idBank", perevod.getIdBank());
        params.addValue("idUser", perevod.getIdUser());
        namedParameterJdbcTemplate.update(createQueryInsert, params, keyHolder);
        long createdPerevodId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdPerevodId);
    }

    @Override
    public Perevod update(Perevod perevod) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCard1", perevod.getIdCard1());
        params.addValue("idCard2", perevod.getIdCard2());
        params.addValue("typeCard1", perevod.getTypeCard1());
        params.addValue("typeCard2", perevod.getTypeCard1());
        params.addValue("srokCard", perevod.getSrokCard());
        params.addValue("securityCode1", perevod.getSucurityCode1());
        params.addValue("accountId", perevod.getIdAccount());
        params.addValue("summaPerevoda", perevod.getSummaPerevoda());
        params.addValue("valutaPerevoda", perevod.getValuta());
        params.addValue("stavkaPerevoda", perevod.getStavka());
        params.addValue("komisyaPerevoda", perevod.getStavka());
        params.addValue("datePerevoda", perevod.getDatePerevoda());
        params.addValue("idBank", perevod.getIdBank());
        params.addValue("idUser", perevod.getIdUser());

        params.addValue("idPerevoda", perevod.getIdPerevoda());

        namedParameterJdbcTemplate.update(createQueryUpdate, params);
        return findById(perevod.getIdAccount());
    }

    @Override
    public List<Perevod> search(String query, Integer limit, Integer offset) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);
        return namedParameterJdbcTemplate.query(searchQuery, params, this::getPerevodRowMapper);
    }
}
