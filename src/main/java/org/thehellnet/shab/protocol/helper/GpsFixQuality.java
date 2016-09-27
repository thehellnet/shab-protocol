package org.thehellnet.shab.protocol.helper;

/**
 * Created by sardylan on 21/08/16.
 */
public enum GpsFixQuality {
    INVALID,
    GPS,
    DGPS,
    PPS,
    REAL_TIME_KINEMATIC,
    FLOAT_RTK,
    ESTIMATED,
    MANUAL_INPUT,
    SIMULATION;

    public static GpsFixQuality fromNumber(int number) {
        switch (number) {
            case 1:
                return GPS;
            case 2:
                return DGPS;
            case 3:
                return PPS;
            case 4:
                return REAL_TIME_KINEMATIC;
            case 5:
                return FLOAT_RTK;
            case 6:
                return ESTIMATED;
            case 7:
                return MANUAL_INPUT;
            case 8:
                return SIMULATION;
            default:
                return INVALID;
        }
    }

    public int toInt() {
        switch (this) {
            case GPS:
                return 1;
            case DGPS:
                return 2;
            case PPS:
                return 3;
            case REAL_TIME_KINEMATIC:
                return 4;
            case FLOAT_RTK:
                return 5;
            case ESTIMATED:
                return 6;
            case MANUAL_INPUT:
                return 7;
            case SIMULATION:
                return 8;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case GPS:
                return "GPS fix (SPS)";
            case DGPS:
                return "DGPS fix";
            case PPS:
                return "PPS fix";
            case REAL_TIME_KINEMATIC:
                return "Real Time Kinematic";
            case FLOAT_RTK:
                return "Float RTK";
            case ESTIMATED:
                return "Estimated (dead reckoning)";
            case MANUAL_INPUT:
                return "Manual input";
            case SIMULATION:
                return "Simulation mode";
            default:
                return "Invalid";
        }
    }
}
