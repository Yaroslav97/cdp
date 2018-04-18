package com.epam.cdp.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputModeMockTest {

    private static final String EXPRESSION = "50-(5*3)";
    private static final String RESULT = "35";

    @Mock
    BufferedReader bufferedReader;

    @Test
    public void readConsoleTest() throws Exception {
        when(bufferedReader.readLine()).thenReturn(EXPRESSION).thenReturn("stop");
        ConsoleMode consoleMode = new ConsoleMode();
        assertTrue(consoleMode.manualEnter(bufferedReader).get(EXPRESSION).equals(RESULT));
    }

    @Test
    public void readFileTest() throws IOException {
        when(bufferedReader.readLine()).thenReturn(EXPRESSION).thenReturn(null);
        FileMode fileMode = new FileMode(new File("test.txt"), bufferedReader);
        assertEquals(fileMode.calculate(), RESULT);
    }

}
