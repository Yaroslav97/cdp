package com.epam.cdp.calculator;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TemporaryFileTest {

    private static final String EXPRESSION = "50-(5*3)";
    private static final String RESULT = "35";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void tempFolderTest() throws IOException {
        File file = temporaryFolder.newFile("test.txt");
        FileUtils.writeStringToFile(file, EXPRESSION);

        FileMode fileMode = new FileMode(file, new BufferedReader(new FileReader(file)));
        fileMode.calculate();

        assertEquals(FileUtils.readLines(file).get(1), RESULT);
    }
}
