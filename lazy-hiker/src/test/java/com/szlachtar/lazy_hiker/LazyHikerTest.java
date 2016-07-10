package com.szlachtar.lazy_hiker;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.szlachtar.lazy_hiker.Main.main;

public class LazyHikerTest {
    @Test
    public void shouldPassExample1() throws Exception {
        //given
        String input =  "1,1,1,1\n" +
                        "1,2,3,1\n" +
                        "1,5,3,1\n" +
                        "1,1,1,1";
        String expected = "RRRDDD\n" +
                          "DDDRRR";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldPassExample2() throws Exception {
        //given
        String input =  "1,9,9,9\n" +
                        "1,1,1,9\n" +
                        "9,9,1,9\n" +
                        "9,1,1,9\n" +
                        "9,1,9,9\n" +
                        "9,1,1,1";
        String expected = "DRRDDLDDRR";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldWorkWithUpMove() throws Exception {
        //given
        String input =  "1,1,1,1,1,1,1,1\n" +
                        "9,9,9,9,9,9,9,1\n" +
                        "9,9,1,1,1,1,9,1\n" +
                        "9,9,1,9,9,1,1,1\n" +
                        "9,9,1,9,9,9,9,9\n" +
                        "1,1,1,1,1,1,1,1";
        String expected = "RRRRRRRDDDLLULLLDDDRRRRR";

        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldWorkForSmallMatrix() throws Exception {
        //given
        String input =  "1,1\n" +
                        "1,1";
        String expected =   "RD\n" +
                            "DR";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldWorkWithSmallMatrixAndOnlyOneSolution() throws Exception {
        //given
        String input =  "1,5\n" +
                        "1,1";
        String expected =   "DR";
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
