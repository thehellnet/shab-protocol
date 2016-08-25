package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

/**
 * Created by sardylan on 03/08/16.
 */
public class HabTelemetryLine extends Line {

    private static final Command COMMAND = Command.HAB_TELEMETRY;
    public static final String COMMAND_TAG = "HT";

    private float intTemp;
    private float extTemp;
    private float extAlt;

    public HabTelemetryLine() {
        super(COMMAND);
    }

    public HabTelemetryLine(String rawLine) throws AbstractProtocolException {
        super(COMMAND, rawLine);
    }

    @Override
    public String serializeLine() {
        return String.format("%s|%.01f|%.01f|%.01f",
                COMMAND_TAG,
                intTemp, extTemp, extAlt);
    }

    @Override
    protected void parse(String[] items) throws AbstractProtocolException {
        if (!items[1].equals(COMMAND_TAG) || items.length != 5) {
            throw new ParseLineException();
        }

        intTemp = Float.parseFloat(items[2]);
        extTemp = Float.parseFloat(items[3]);
        extAlt = Float.parseFloat(items[4]);
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
