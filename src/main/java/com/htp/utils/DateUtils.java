package com.htp.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getMonthAgoDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

}
