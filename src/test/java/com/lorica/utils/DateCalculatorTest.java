package com.lorica.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vidura Mudalige
 */
public class DateCalculatorTest {

    private DateCalculator dateCalculator;

    @Before
    public void setUp() {
        CustomDate baseDate = mock(CustomDate.class);
        when(baseDate.getYear()).thenReturn(1901);
        when(baseDate.getMonth()).thenReturn(1);
        when(baseDate.getDay()).thenReturn(1);
        dateCalculator = new DateCalculator(baseDate);
    }

    @Test
    public void Should_ReturnTheLeapDaysCount_When_CustomDatesAreValid() {
        CustomDate dateOne = mock(CustomDate.class);

        when(dateOne.getYear()).thenReturn(1920);
        when(dateOne.getMonth()).thenReturn(2);
        when(dateOne.getDay()).thenReturn(29);

        int expected = 4;
        int actual = dateCalculator.getNumberOfLeapDaysFromBaseDate(dateOne);

        assertEquals(expected, actual);
    }

    @Test
    public void Should_ReturnTheNumberOfDaysFromTheBaseDate_When_AValidCustomDateIsGiven() {
        CustomDate date = mock(CustomDate.class);
        when(date.getYear()).thenReturn(1901);
        when(date.getMonth()).thenReturn(1);
        when(date.getDay()).thenReturn(20);

        long expected = 20;
        long actual = dateCalculator.getNumberOfDaysFromTheBaseDate(date);

        assertEquals(expected, actual);
    }
}