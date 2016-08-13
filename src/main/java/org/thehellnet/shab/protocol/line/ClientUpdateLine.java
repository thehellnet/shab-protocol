package org.thehellnet.shab.protocol.line;

import org.thehellnet.shab.protocol.Command;
import org.thehellnet.shab.protocol.exception.AbstractProtocolException;
import org.thehellnet.shab.protocol.exception.ParseLineException;

import java.util.Locale;

/**
 * Created by sardylan on 03/08/16.
 */
public class ClientUpdateLine extends Line {

    private static final Command COMMAND = Command.CLIENT_UPDATE;
    public static final String COMMAND_TAG = "CU";

    private String id;
    private double latitude;
    private double longitude;
    private double altitude;

    public ClientUpdateLine() {
        super(COMMAND);
    }

    public ClientUpdateLine(String rawLine) throws AbstractProtocolException {
        super(COMMAND, rawLine);
    }

    @Override
    public String serializeLine() {
        return String.format(Locale.US, "%s|%s|%.8f|%.8f|%.1f", COMMAND_TAG, id, latitude, longitude, altitude);
    }

    @Override
    protected void parse(String[] items) throws AbstractProtocolException {
        if (!items[1].equals(COMMAND_TAG) || items.length != 6) {
            throw new ParseLineException();
        }

        id = items[2];

        try {
            latitude = Float.parseFloat(items[3]);
            longitude = Float.parseFloat(items[4]);
            altitude = Float.parseFloat(items[5]);
        } catch (NumberFormatException e) {
            throw new ParseLineException();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
