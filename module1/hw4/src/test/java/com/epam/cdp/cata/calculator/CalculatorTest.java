package com.epam.cdp.cata.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    @Spy
    private Calculator calculator;

    @Test
    public void testAdd() throws Exception {
        assertEquals(68, calculator.add("43,23,2"));
    }

    @Test
    public void testAddWithEmptyValue() throws Exception {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testAddWithLines() throws Exception {
        assertEquals(68, calculator.add("43\n23,2"));
    }

    @Test
    public void testAddWithDelimiter() throws Exception {
        assertEquals(68, calculator.add("//;\n43;23;2"));
    }

    @Test(expected = Exception.class)
    public void testAddWithNegativeNumbers() throws Exception {
        assertEquals(21, calculator.add("//;\n46;-23;-2"));
    }

    @Test
    public void testAddShouldSkipBigNumbers() throws Exception {
        assertEquals(68, calculator.add("//;\n43;23;2;2323"));
    }

    @Test
    public void testAddDelimiterWithAnyLength() throws Exception {
        assertEquals(68, calculator.add("//;;;\n43;;;23;;;2;;;2323"));
    }

    @Test
    public void testAddWithMultipleDelimiter() throws Exception {
        assertEquals(68, calculator.add("//[;;;][---]\n43;;;---23;;;---2;;;---2323"));
    }

}