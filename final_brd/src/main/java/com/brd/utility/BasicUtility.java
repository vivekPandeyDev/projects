package com.brd.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasicUtility {

    private BasicUtility() {
    }

    public static long parseLong(String str) {
        return str == null ? -1 :  Long.parseLong(str);
    }

    public static LocalDate dateParse(String date) {
        if (date == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
