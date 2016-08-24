package org.thehellnet.shab.protocol.line;

import org.junit.Test;
import org.thehellnet.shab.protocol.exception.CommandNotSupportedException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

import static org.junit.Assert.assertEquals;
import static org.thehellnet.shab.protocol.utility.Checksum.checksum16;

/**
 * Created by sardylan on 13/08/16.
 */
public class LineFactoryTest {

    private String input;
    private Line expected;
    private Line actual;

    @Test
    public void testChecksum16() throws Exception {
        String input = "0123";
        int expexted = 0x30 + 0x31 + 0x32 + 0x33;
        int acutal = checksum16(input);
        assertEquals(expexted, acutal);
    }

    @Test
    public void testAddChecksum() throws Exception {
        String input = "0123";
        String expected = "00C6|0123";
        String actual = LineFactory.addChecksum(input);
        assertEquals(expected, actual);
    }

    @Test(expected = ParseLineException.class)
    public void testParseLineExceptionNull() throws Exception {
        LineFactory.parse(null);
    }

    @Test(expected = ParseLineException.class)
    public void testParseLineExceptionEmpty() throws Exception {
        LineFactory.parse("");
    }

    @Test(expected = ParseLineException.class)
    public void testParseLineExceptionNoSplit() throws Exception {
        LineFactory.parse("0000");
    }

    @Test(expected = ParseLineException.class)
    public void testParseLineExceptionEmptyLine() throws Exception {
        LineFactory.parse("0000|");
    }

    @Test(expected = CommandNotSupportedException.class)
    public void testParseLineExceptionCommandNotSupported() throws Exception {
        LineFactory.parse("00B4|ZZ");
    }
}