package com.szlahtar.prime_tables;


import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.szlachtar.prime_tables.Main.main;

public class PrimeTablesTest {
    @Test
    public void shouldPassExample() throws Exception {
        //given
        String input =  "1,1,1,1\n" +
                        "1,2,1,1\n" +
                        "1,1,1,1";
        String expected = "11";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldReturnZeroWhenNoPrimeTablesFound() throws Exception {
        //given
        String input =  "2,2,2,2,2\n" +
                        "2,2,2,2,2\n" +
                        "2,2,2,2,2\n" +
                        "2,2,2,2,2";
        String expected = "0";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldReturnWorkWithSmallMatrix() throws Exception {
        //given
        String input =  "1,1\n" +
                        "1,2";
        String expected = "1";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    private void verifyOutput(String expected, ByteArrayOutputStream output) {
        Assert.assertEquals(expected, output.toString());
    }


    private ByteArrayOutputStream redirectInputOutput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }
}
