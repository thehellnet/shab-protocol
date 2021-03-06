package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.entity.Hab;
import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

import java.util.Locale;

/**
 * Created by sardylan on 03/08/16.
 */
public class HabPositionLine extends Line {

    private static final Command COMMAND = Command.HAB_POSITION;
    public static final String COMMAND_TAG = "HP";

    private long timestamp;
    private int fixStatus;
    private double latitude;
    private double longitude;
    private double altitude;

    public HabPositionLine() {
        super(COMMAND);
    }

    public HabPositionLine(String rawLine) throws AbstractProtocolException {
        super(COMMAND, rawLine);
    }

    public HabPositionLine(Hab hab) {
        super(COMMAND);

        if (hab == null) {
            return;
        }

        fixStatus = hab.getFixStatus().toInt();

        if (hab.getPosition() != null) {
            latitude = hab.getPosition().getLatitude();
            longitude = hab.getPosition().getLongitude();
            altitude = hab.getPosition().getAltitude();
        }
    }

    @Override
    public String serializeLine() {
        return String.format(Locale.US, "%s|%d|%d|%.8f|%.8f|%.1f", COMMAND_TAG, timestamp, fixStatus, latitude, longitude, altitude);
    }

    @Override
    protected void parse(String[] items) throws AbstractProtocolException {
        if (!items[1].equals(COMMAND_TAG) || items.length != 7) {
            throw new ParseLineException();
        }

        timestamp = Long.parseLong(items[2]);
        fixStatus = Integer.parseInt(items[3]);
        latitude = Float.parseFloat(items[4]);
        longitude = Float.parseFloat(items[5]);
        altitude = Float.parseFloat(items[6]);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getFixStatus() {
        return fixStatus;
    }

    public void setFixStatus(int fixStatus) {
        this.fixStatus = fixStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
