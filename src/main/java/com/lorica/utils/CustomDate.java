package com.lorica.utils;

/**
 * @author Vidura Mudalige
 */
public class CustomDate {

    private int year;
    private int month;
    private int day;

    public CustomDate(String inputDate, String regex) {
        String[] dateTokens = inputDate.split(regex);
        this.day = Integer.parseInt(dateTokens[0]);
        this.month = Integer.parseInt(dateTokens[1]);
        this.year = Integer.parseInt(dateTokens[2]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
