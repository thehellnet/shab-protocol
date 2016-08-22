package org.thehellnet.shab.protocol.utility;

/**
 * Created by sardylan on 22/08/16.
 */
public final class Checksum {

    public static int checksum16(String rawLine) {
        int ret = 0;

        for (int i = 0; i < rawLine.length(); i++) {
            ret += rawLine.charAt(i);
            ret %= 0xFFFF;
        }

        return ret;
    }
}
