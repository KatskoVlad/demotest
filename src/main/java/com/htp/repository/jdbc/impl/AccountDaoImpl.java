package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.Account;
import com.htp.repository.jdbc.AccountDao;
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
@Qualifier("accountDaoImpl")
public class AccountDaoImpl implements AccountDao {

    private static final String ID_ACCOUNTS = "id_accounts";
    private static final String ACCOUNT = "account";
    private static final String BALANS = "balans";
    private static final String DATE_CREATION = "balans";
    private static final String ID_USER_ACC = "id_user";

    final String findAllQuery = "select * from accounts";
    final String deleteAccount = "delete from accounts where id_accounts = :idAccount";
    final String createQueryUpdate = "UPDATE accounts set code_bic=:codeBic, " +
            "description=:description, inn=:inn, name=:name, adress=:adress, phone=:phone where id_bank = :idBank";
    final String createQueryInsert = "INSERT INTO accounts (code_bic, description, inn, name, adress, phone) " +
            "VALUES (:codeBic, :description, :inn, :name, :age, :adress, :phone);";
    final String findByIdAccount = "select * from accounts where id_accounts = :idAccount";
    final String findByAccountStr = "select * from accounts where account = :account";
    final String searchQuery = "select * from accounts where lower(account) LIKE lower(:query) " +
            "limit :lim offset :off";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private Account getAccountRowMapper(ResultSet resultSet, int i) throws SQLException {

        Account account = new Account();

        account.setIdAccount(resultSet.getLong(ID_ACCOUNTS));
        account.setTypeAccount(resultSet.getString(ACCOUNT));
        account.setBalans(resultSet.getDouble(BALANS));
        account.setDateRegAcc(resultSet.getTimestamp(DATE_CREATION));
        account.setDateRegAcc(resultSet.getTimestamp(ID_USER_ACC));

        return account;
    }

    @Override
    public List<Account> findAll() {
        return namedParameterJdbcTemplate.query(findAllQuery, this::getAccountRowMapper);
    }

    @Override
    public Account findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idAccount", id);
        return namedParameterJdbcTemplate.queryForObject(findByIdAccount, params, this::getAccountRowMapper);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idAccount", id);
        namedParameterJdbcTemplate.update(deleteAccount, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Account save(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account", account.getTypeAccount());
        params.addValue("balans", account.getBalans());
        params.addValue("dateReg", account.getDateRegAcc());
        params.addValue("idUser", account.getIdUser());
        namedParameterJdbcTemplate.update(createQueryInsert, params, keyHolder);
        long createdAccountId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdAccountId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Account update(Account account) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account", account.getTypeAccount());
        params.addValue("balans", account.getBalans());
        params.addValue("createDate", account.getDateRegAcc());
        params.addValue("idUser", account.getIdUser());

        params.addValue("idAccount", account.getIdAccount());

        namedParameterJdbcTemplate.update(createQueryUpdate, params);
        return findById(account.getIdAccount());
    }

    @Override
    public Long findByAccount(String accountName) {
        Account account = new Account();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("account", accountName);
        account = namedParameterJdbcTemplate.queryForObject(findByAccountStr, params, this::getAccountRowMapper);
        return account.getIdAccount();
    }

    @Override
    public List<Account> search(String query, Integer limit, Integer offset) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getAccountRowMapper);
    }
}
