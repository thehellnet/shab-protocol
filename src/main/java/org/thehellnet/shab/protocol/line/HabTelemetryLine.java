package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.entity.Hab;
import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

/**
 * Created by sardylan on 03/08/16.
 */
public class HabTelemetryLine extends Line {

    private static final Command COMMAND = Command.HAB_TELEMETRY;
    public static final String COMMAND_TAG = "HT";

    private long timestamp;
    private float intTemp;
    private float extTemp;
    private float extAlt;

    public HabTelemetryLine() {
        super(COMMAND);
    }

    public HabTelemetryLine(String rawLine) throws AbstractProtocolException {
        super(COMMAND, rawLine);
    }

    public HabTelemetryLine(Hab hab) {
        super(COMMAND);

        if (hab == null) {
            return;
        }

        intTemp = hab.getIntTemp();
        extTemp = hab.getExtTemp();
        extAlt = hab.getExtAlt();
    }

    @Override
    public String serializeLine() {
        return String.format("%s|%d|%.01f|%.01f|%.01f",
                COMMAND_TAG,
                timestamp,
                intTemp, extTemp, extAlt);
    }

    @Override
    protected void parse(String[] items) throws AbstractProtocolException {
        if (!items[1].equals(COMMAND_TAG) || items.length != 6) {
            throw new ParseLineException();
        }

        timestamp = Long.parseLong(items[2]);
        intTemp = Float.parseFloat(items[3]);
        extTemp = Float.parseFloat(items[4]);
        extAlt = Float.parseFloat(items[5]);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getIntTemp() {
        return intTemp;
    }

    public void setIntTemp(float intTemp) {
        this.intTemp = intTemp;
    }

    public float getExtTemp() {
        return extTemp;
    }

    public void setExtTemp(float extTemp) {
        this.extTemp = extTemp;
    }

    public float getExtAlt() {
        return extAlt;
    }

    public void setExtAlt(float extAlt) {
        this.extAlt = extAlt;
    }
}
