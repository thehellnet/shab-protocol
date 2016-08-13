package org.thehellnet.shab.protocol;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sardylan on 13/08/16.
 */
public class LineFactoryTest {

    private String input;
    private String expected;
    private String actual;

    @Test
    public void addChecksum() throws Exception {
        input = "CU|b8b342a13659c53f|39.43|9.57|36.22";
        expected = "0986|CU|b8b342a13659c53f|39.43|9.57|36.22";
        actual = LineFactory.addChecksum(input);
        assertEquals(expected, actual);
    }
}