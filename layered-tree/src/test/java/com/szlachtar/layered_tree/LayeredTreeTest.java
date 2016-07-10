package com.szlachtar.layered_tree;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.szlachtar.layered_tree.Main.main;

public class LayeredTreeTest {

    @Test
    public void shouldProduceSameResultForExample1() throws Exception {
        //given
        String input = "5,4,3,2,1";
        String expected =   "1\n" +
                            "3,2\n" +
                            "4,5,_,_";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldProduceSameResultForExample2() throws Exception {
        //given
        String input = "9,8,7,6,5,4,3,2,1";
        String expected = "1\n" +
                "3,2\n" +
                "4,5,6,7\n" +
                "_,_,_,_,_,_,9,8";

        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldPassForBiggerTree() throws Exception {
        //given
        String input = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33";
        String expected = "1\n" +
                "3,2\n" +
                "4,5,6,7\n" +
                "15,14,13,12,11,10,9,8\n" +
                "16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31\n" +
                "_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,_,33,32";

        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldWorkForSingleNodeTree() throws Exception {
        //given
        String input = "1";
        String expected = "1";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldWorkForTwoElementTree() throws Exception {
        //given
        String input = "1,2";
        String expected =   "1\n" +
                            "_,2";
        ByteArrayOutputStream output = redirectInputOutput(input);

        //when
        main(new String[]{});

        //then
        verifyOutput(expected, output);
    }

    @Test
    public void shouldWorkForThreeElementTree() throws Exception {
        //given
        String input = "1,2,3";
        String expected =   "1\n" +
                "3,2";
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
