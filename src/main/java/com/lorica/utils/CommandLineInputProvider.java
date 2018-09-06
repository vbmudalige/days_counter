package com.lorica.utils;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vidura Mudalige
 */
public class CommandLineInputProvider implements InputProvider {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineInputProvider.class);
    private static final String FIRST_INPUT_DATE = "firstInputDate";
    private static final String SECOND_INPUT_DATE = "secondInputDate";

    private String firstInputDate;
    private String secondInputDate;

    CommandLineInputProvider(String[] args) {
        processInputs(args);
    }

    /**
     * @return the first input date
     */
    public String getFirstInputDate() {
        return this.firstInputDate;
    }

    /**
     * @return the second input date
     */
    public String getSecondInputDate() {
        return this.secondInputDate;
    }

    private void processInputs(String[] args) {
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
        this.firstInputDate = cmd.getOptionValue(FIRST_INPUT_DATE);
        this.secondInputDate = cmd.getOptionValue(SECOND_INPUT_DATE);
    }
}