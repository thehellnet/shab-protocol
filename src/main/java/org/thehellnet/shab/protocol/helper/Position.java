package org.thehellnet.shab.protocol.helper;

import org.thehellnet.shab.protocol.line.ClientUpdateLine;
import org.thehellnet.shab.protocol.line.HabPositionLine;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by sardylan on 03/08/16.
 */
public class Position implements Serializable {

    private double latitude;
    private double longitude;
    private double altitude;

    public Position() {
    }

    public Position(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Position(ClientUpdateLine line) {
        if (line != null) {
            latitude = line.getLatitude();
            longitude = line.getLongitude();
            altitude = line.getAltitude();
        }
    }

    public Position(HabPositionLine line) {
        if (line != null) {
            latitude = line.getLatitude();
            longitude = line.getLongitude();
            altitude = line.getAltitude();
        }
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

    @Override
    public String toString() {
        return String.format(Locale.US, "Lat: %s, Long: %s, Alt: %s", latitude, longitude, altitude);
    }
}
