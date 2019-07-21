package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.Bank;
import com.htp.repository.jdbc.CatalogBanksDao;
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
@Qualifier("catalogBanksDaoImpl")
public class CatalogBanksDaoImpl implements CatalogBanksDao {

    private static final String ID_BANK = "id_bank";
    private static final String CODE_BIC = "code_bic";
    private static final String DISCRIPTION = "description";
    private static final String INN = "inn";
    private static final String NAME = "name";
    private static final String ADRESS = "adress";
    private static final String PHONE = "phone";

    final String findAllQuery = "select * from catalog_banks";
    final String findById = "select * from catalog_banks where id_bank = :idBank";
    final String deleteBank = "delete from catalog_banks where id_bank = :idBank";
    final String createQueryUpdate = "UPDATE catalog_banks set code_bic=:codeBic, " +
            "description=:description, inn=:inn, name=:nameBank, adress=:adress, phone=:phone where id_bank = :idBank";
    final String createQueryIns = "INSERT INTO catalog_banks (code_bic, description, inn, name, adress, phone) " +
            "VALUES (:codeBic, :description, :inn, :nameBank, :adress, :phone);";
    final String findByCodeBicStr = "select * from catalog_banks where code_bic = :codeBic";
    final String searchQuery = "select * from catalog_banks where " +
            "lower(description) LIKE lower(:query) "+
            "lower(nameBank) LIKE lower(:query) or "+
            "lower(inn) LIKE lower(:query) or " +
            "lower(adress) LIKE lower(:query) or " +
            " or limit :lim offset :off";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Bank getCatalogBanksRowMapper(ResultSet resultSet, int i) throws SQLException {

        Bank bank = new Bank();

        bank.setIdBank(resultSet.getLong(ID_BANK));
        bank.setCodeBic(resultSet.getString(CODE_BIC));
        bank.setDescription(resultSet.getString(DISCRIPTION));
        bank.setInn(resultSet.getString(INN));
        bank.setName(resultSet.getString(NAME));
        bank.setAdress(resultSet.getString(ADRESS));
        bank.setPhone(resultSet.getString(PHONE));

        return bank;
    }

    @Override
    public List<Bank> findAll() {
        return namedParameterJdbcTemplate.query(findAllQuery, this::getCatalogBanksRowMapper);
    }

    @Override
    public Bank findById(Long id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idBank", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getCatalogBanksRowMapper);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idBank", id);

        namedParameterJdbcTemplate.update(deleteBank, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Bank save(Bank bank) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codeBic", bank.getCodeBic());
        params.addValue("description", bank.getDescription());
        params.addValue("inn", bank.getInn());
        params.addValue("nameBank", bank.getName());
        params.addValue("adress", bank.getAdress());
        params.addValue("phone", bank.getPhone());

        namedParameterJdbcTemplate.update(createQueryIns, params, keyHolder);

        long createdBankId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdBankId);
    }

    @Override
    public Bank update(Bank bank) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codeBic", bank.getName());
        params.addValue("description", bank.getDescription());
        params.addValue("inn", bank.getInn());
        params.addValue("nameBank", bank.getName());
        params.addValue("adress", bank.getAdress());
        params.addValue("phone", bank.getPhone());

        params.addValue("idBank", bank.getIdBank());

        namedParameterJdbcTemplate.update(createQueryUpdate, params);
        return findById(bank.getIdBank());
    }

    @Override
    public Long findByCodeBic(String codeBic) {
        Bank bank = new Bank();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codeBic", codeBic);
        bank =  namedParameterJdbcTemplate.queryForObject(findByCodeBicStr, params, this::getCatalogBanksRowMapper);
        return bank.getIdBank();
    }

    @Override
    public List<Bank> search(String query, Integer limit, Integer offset) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getCatalogBanksRowMapper);
    }
}
