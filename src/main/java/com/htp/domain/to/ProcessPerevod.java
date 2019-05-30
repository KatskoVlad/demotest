package com.htp.domain.to;

import com.htp.domain.jdbc.Account;
import com.htp.domain.jdbc.Card;
import com.htp.domain.jdbc.Perevod;
import lombok.Data;

@Data
public class ProcessPerevod {

    private Account account;
    private Card card;
    private Perevod perevod;

}
