package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

import java.util.Locale;

/**
 * Created by sardylan on 26/08/16.
 */
public class ServerPingLine extends Line {

    private static final Command COMMAND = Command.SERVER_PING;
    public static final String COMMAND_TAG = "SP";

    private long timestamp;

    public ServerPingLine() {
        super(COMMAND);
    }

    public ServerPingLine(String rawLine) throws AbstractProtocolException {
        super(COMMAND, rawLine);
    }

    @Override
    protected String serializeLine() {
        return String.format(Locale.US, "%s|%d", COMMAND_TAG, timestamp);
    }

    @Override
    protected void parse(String[] items) throws AbstractProtocolException {
        if (!items[1].equals(COMMAND_TAG) || items.length != 3) {
            throw new ParseLineException();
        }

        timestamp = Long.parseLong(items[2]);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
