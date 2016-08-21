package org.thehellnet.shab.protocol;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by sardylan on 21/08/16.
 */
public class Base64Test {

    @Test
    public void decode() throws Exception {
        String input = "dGVzdHN0cmluZw==";
        byte[] expected = "teststring".getBytes();
        byte[] actual = Base64.decode(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void encode() throws Exception {
        byte[] input = "teststring".getBytes();
        String expected = "dGVzdHN0cmluZw==";
        String actual = Base64.encode(input);
        assertEquals(expected, actual);
    }

}