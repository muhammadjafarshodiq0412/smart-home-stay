package com.smarthomestay.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            log.error("Error convert date to string", ex);
        }
        return null;
    }

    public static Date stringToDate(String date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            sdf.setLenient(false);
            return sdf.parse(date);
        } catch (ParseException ex) {
            log.error("Error convert string to date", ex);
        }
        return null;
    }

    public static String changeDateFormat(String date, String oldFormat, String newFormat) {
        try {
            SimpleDateFormat oldSdf = new SimpleDateFormat(oldFormat);
            oldSdf.setLenient(false);

            Date result = oldSdf.parse(date);

            SimpleDateFormat newSdf = new SimpleDateFormat(newFormat);
            newSdf.setLenient(false);

            return newSdf.format(result);
        } catch (Exception ex) {
            log.error("Change date format error", ex);
        }
        return null;
    }
}
