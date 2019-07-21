package com.htp.repository.jdbc;

import com.htp.domain.jdbc.Card;
import com.htp.repository.GenericDao;

import java.util.List;

public interface ReestrBanksCardDao extends GenericDao<Card, Long> {
    Card findByNumberCard(String numberCard);

    List<Card> search(String query, Integer limit, Integer offset);
}
