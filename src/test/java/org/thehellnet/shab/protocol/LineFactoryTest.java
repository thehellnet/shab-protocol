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
        input = "CU|b8b342a13659c53f|1.4|3.5|2.9";
        expected = "0879|CU|b8b342a13659c53f|1.4|3.5|2.9";
        actual = LineFactory.addChecksum(input);
        assertEquals(expected, actual);
    }
}