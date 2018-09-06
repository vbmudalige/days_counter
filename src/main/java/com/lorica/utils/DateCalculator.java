package com.lorica.utils;

/**
 * @author Vidura Mudalige
 */
public class DateCalculator {

    private final CustomDate baseDate;
    private final int[] startingDaysOfEachMonth = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public DateCalculator(CustomDate baseDate) {
        this.baseDate = baseDate;
    }

    /**
     * Calculate the number of leap days from the base date
     *
     * @param date : input custom date
     * @return the number of leap days
     */
    public int getNumberOfLeapDaysFromBaseDate(CustomDate date) {
        int baseYear = baseDate.getYear();
        int dateYear = date.getYear();
        int dateMonth = date.getMonth();

        int leapDaysCount = 0;
        for (int i = baseYear; i <= dateYear; i++) {
            if (i % 4 == 0) {
                leapDaysCount++;
            }
            if (i % 100 == 0 && i % 400 != 0) {
                leapDaysCount--;
            }
        }
        if (dateYear % 4 == 0 && dateMonth < 3) {
            leapDaysCount--;
        }
        return leapDaysCount;
    }

    /**
     * Calculate the number of days from the base date by considering every year has only 365 days
     *
     * @param date : input custom date
     * @return the number of days
     */
    public long getNumberOfDaysFromTheBaseDate(CustomDate date) {
        return 365 * (date.getYear() - baseDate.getYear()) + startingDaysOfEachMonth[date.getMonth() - 1] + date.getDay();
    }
}
