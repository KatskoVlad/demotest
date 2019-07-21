package com.htp.repository.jdbc;

import com.htp.domain.jdbc.Card;
import com.htp.repository.GenericDao;

import java.util.List;

public interface CardDao extends GenericDao<Card, Long> {
    Long findByNumberCard(String cardNumber);

    List<Card> search(String query, Integer limit, Integer offset);
}
