package com.lorica.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vidura Mudalige
 */
public class DaysCounter {

    private static final Logger logger = LoggerFactory.getLogger(DaysCounter.class);
    private static final String BASE_DATE = "01/01/1901";

    public static void main(String[] args) {

        InputProvider inputProvider = new CommandLineInputProvider(args);

        final String firstInputDate = inputProvider.getFirstInputDate();
        final String secondInputDate = inputProvider.getSecondInputDate();

        logger.info("First input date is {} and second input date is {}", firstInputDate, secondInputDate);

        CustomDate dateOne = new CustomDate(firstInputDate.trim(), "/");
        CustomDate dateTwo = new CustomDate(secondInputDate.trim(), "/");

        long noOfFullDays = getNumberOfFullDays(dateOne, dateTwo);

        logger.info("The number of full days between {} and {} are {}", firstInputDate, secondInputDate, noOfFullDays);
    }

    /*
    Calculate the value of |(dateOne - baseDate) - (dateTwo - baseDate)| + |leapDays(dateOne - baseDate) - leapDays(dateTwo - baseDate)| - 1
     */
    private static long getNumberOfFullDays(CustomDate dateOne, CustomDate dateTwo) {
        DateCalculator dateCalculator = new DateCalculator(new CustomDate(BASE_DATE, "/"));

        long noOfLeapDaysForTheDateOneFromTheBaseDate = dateCalculator.getNumberOfLeapDaysFromBaseDate(dateOne);
        long noOfLeapDaysForTheDateTwoFromTheBaseDate = dateCalculator.getNumberOfLeapDaysFromBaseDate(dateTwo);
        long noOfDaysForTheDateOneFromTheBaseDate = dateCalculator.getNumberOfDaysFromTheBaseDate(dateOne);
        long noOfDaysForTheDateTwoFromTheBaseDate = dateCalculator.getNumberOfDaysFromTheBaseDate(dateTwo);

        return Math.abs(noOfLeapDaysForTheDateOneFromTheBaseDate - noOfLeapDaysForTheDateTwoFromTheBaseDate)
                + Math.abs(noOfDaysForTheDateOneFromTheBaseDate - noOfDaysForTheDateTwoFromTheBaseDate) - 1;
    }
}