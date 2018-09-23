package com.spring.oracle.api.springoracleapi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static Date parseDate(String dateStr) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Error while parsing date : " + e.getMessage(), e);
        }
    }

    public static String formatDate(Date d) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        return df.format(d);
    }
}
