package com.lorica.utils;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vidura Mudalige
 */
public class DaysCounter {

    private static final Logger logger = LoggerFactory.getLogger(DaysCounter.class);

    private final static String FIRST_INPUT_DATE = "firstInputDate";
    private final static String SECOND_INPUT_DATE = "secondInputDate";
    private final static String BASE_DATE = "01/01/1901";

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(FIRST_INPUT_DATE, true, "First input date for the full days count calculation").
                addOption(SECOND_INPUT_DATE, true, "Second input date for the full days count calculation");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Cannot parse the inputs", e);
        }
        if (!cmd.hasOption(FIRST_INPUT_DATE) || !cmd.hasOption(SECOND_INPUT_DATE)) {
            logger.error("Please provide two dates");
            return;
        }
        final String firstInputDate = cmd.getOptionValue(FIRST_INPUT_DATE);
        final String secondInputDate = cmd.getOptionValue(SECOND_INPUT_DATE);

        logger.info("First input date is {} and second input date is {}", firstInputDate, secondInputDate);

        CustomDate dateOne = new CustomDate(firstInputDate.trim(), "/");
        CustomDate dateTwo = new CustomDate(secondInputDate.trim(), "/");

        DateCalculator dateCalculator = new DateCalculator(new CustomDate(BASE_DATE, "/"));

        long noOfLeapDaysForTheDateOneFromTheBaseDate = dateCalculator.getNumberOfLeapDaysFromBaseDate(dateOne);
        long noOfLeapDaysForTheDateTwoFromTheBaseDate = dateCalculator.getNumberOfLeapDaysFromBaseDate(dateTwo);
        long noOfDaysForTheDateOneFromTheBaseDate = dateCalculator.getNumberOfDaysFromTheBaseDate(dateOne);
        long noOfDaysForTheDateTwoFromTheBaseDate = dateCalculator.getNumberOfDaysFromTheBaseDate(dateTwo);

        long noOfFullDays = Math.abs(noOfLeapDaysForTheDateOneFromTheBaseDate - noOfLeapDaysForTheDateTwoFromTheBaseDate)
                + Math.abs(noOfDaysForTheDateOneFromTheBaseDate - noOfDaysForTheDateTwoFromTheBaseDate) - 1;

        logger.info("The number of full days between {} and {} are {}", firstInputDate, secondInputDate, noOfFullDays);
    }
}
